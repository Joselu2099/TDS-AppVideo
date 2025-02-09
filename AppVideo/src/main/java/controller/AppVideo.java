package controller;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;
import dao.DAOVideo;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import umu.tds.componente.VideosLoader;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;

public class AppVideo {

    public static final int MIN_PASSWORD_LENGTH = 8;
    private static AppVideo uniqueInstance = null;
    private DAOFactory factory;
    private User currentUser;
    private List<Playlist> currentPlaylists;
    private IFilter filter;
    private VideosLoader videosLoader;

    private VideoRepository videoRepository;
    private UserRepository userRepository;

    private List<Runnable> filteredVideoChangedListeners;
    private List<Runnable> recentVideoChangedListeners;
    private List<Consumer<Boolean>> premiumStatusChangedListeners;

    private AppVideo() {
        try {
            factory = DAOFactory.getInstance();
            videoRepository = VideoRepository.getInstance();
            userRepository = UserRepository.getInstance();
            videosLoader = VideosLoader.getInstance();

            filteredVideoChangedListeners = new ArrayList<>();
            recentVideoChangedListeners = new ArrayList<>();
            premiumStatusChangedListeners = new ArrayList<>();

            currentPlaylists = new ArrayList<>();

            videosLoader.subscribeNewVideoLoaded( videosXML ->{
                List<Video> newVideo = xmlVideoAdapter(videosXML);
                newVideo.forEach(this::persistVideo);
                notifieFilteredVideoChanged();
            });
        } catch (DAOException e) {
            e.printStackTrace();
        }
        filter = new NoFilter();
    }

    public static AppVideo getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new AppVideo();
        return uniqueInstance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        this.filter = currentUser.getFilter();
        applyFilter(filter);
    }

    public String changeShortTitleOfVideo(Video video, int num){
        return video.getShortTitle(num);
    }

    public void subscribeFilteredVideoChange(Runnable callback){
        if (callback != null)
            filteredVideoChangedListeners.add(callback);
    }

    public void unsubscribeFilteredVideoChange(Runnable callback){
        filteredVideoChangedListeners.remove(callback);
    }

    public List<Video> getMostTopViewVideos(int count){
        return getFilteredVideoList().stream().sorted(new Video.viewComparator().reversed()::compare).limit(count).collect(Collectors.toList());

    }

    public void notifiePremiumStatusChange(){
        premiumStatusChangedListeners.forEach(c->c.accept(isCurrentUserPremium()));
    }
    public void subscribePremiumStatusChange(Consumer<Boolean> isPremiumCallback){
        if (isPremiumCallback != null)
            premiumStatusChangedListeners.add(isPremiumCallback);
    }
    public void unsubscribePremiumStatusChange(Consumer<Boolean> isPremiumCallback){
        premiumStatusChangedListeners.remove(isPremiumCallback);
    }

    public boolean login(String username, String password) {
        User user = userRepository.getUser(username);
        if (user != null && checkPassword(password, user.getPassword())) {
            this.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public boolean isUserRegistered(String username) {
        return userRepository.isUserRegistered(username);
    }

    public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
        if (isUserRegistered(username)) return false;
        User user = new User(name, surname, mail, username, encodePassword(password), dateOfBirth);
        return registerUser(user);
    }

    public boolean registerUser(User user) {
        if (isUserRegistered(user.getUsername())) return false;
        user.setNightMode(false);
        DAOUser daoUser = factory.getDAOUser();
        daoUser.create(user);
        userRepository.addUser(user);
        return true;
    }

    public boolean removeUser(String username) {
        if (isUserRegistered(username)){
            DAOUser daoUser = factory.getDAOUser();
            daoUser.delete(userRepository.getUser(username));
        }
        return userRepository.removeUser(username);
    }

    public boolean removeVideo(String url) {
        if (isVideoPersisted(url)){
            DAOVideo daoVideo = factory.getDAOVideo();
            if (daoVideo.delete(videoRepository.getVideo(url))){
                return videoRepository.removeVideo(url);
            }
        }
        return false;
    }

    public void loadVideos(String file){
        videosLoader.loadVideoFromXML(file);
    }

    public boolean isCurrentUserPremium(){
        return getCurrentUser().isPremium();
    }

    private void notifieFilteredVideoChanged(){
        // Usamos runnable porque AppVideo es un singleton y no hay problema de visibilidad,
        // y no sabemos que dato necesita cuando pasa el evento.
        filteredVideoChangedListeners.forEach(Runnable::run);
    }
    
    public List<Video> searchVideos(String text,Set<Label> labelSet) {
    	return videoRepository.getUnmodifiableFilteredVideoSet().stream()
				.parallel()
				.filter(v-> v.getTitle().contains(text))
				.filter(v -> {
					if (labelSet != null && !labelSet.isEmpty())
						return v.getLabels().stream().parallel().anyMatch(labelSet::contains);
					return true;
				}) // OR filter
				.collect(Collectors.toList());
    }

    public List<Playlist> searchPlaylists(String text) {
        return currentPlaylists.stream()
                .parallel()
                .filter(p-> p.getTitle().contains(text))
                .collect(Collectors.toList());
    }

    public boolean isVideoPersisted(String url){
        return videoRepository.isVideoPresent(url);
    }

    public void persistVideo(Video video){
        if (isVideoPersisted(video.getUrl()))
            return;
        factory.getDAOVideo().create(video); // Side effect: update video ids
        boolean test = filter.test(video,getCurrentUser());
        videoRepository.addVideo(video,test);
        if (test){
            notifieFilteredVideoChanged();
        }
    }

    public boolean isPlaylistInCurrentUser(String title){
        return getCurrentUser().hasPlaylist(title);
    }

    // Factoria
    public boolean createPlaylist(Playlist playlist){
//        System.out.println("isPlaylistRegistered? -> " + getCurrentUser().isPlaylistRegistered(title));
        if(isPlaylistInCurrentUser(playlist.getTitle())) {
            return false;
        }
        factory.getDAOPlaylist().create(playlist);
        getCurrentUser().addOrReplacePlaylist(playlist);
        factory.getDAOUser().updateProfile(getCurrentUser());
        updateFilterForCurrentPlaylists(filter);
        return true;
    }

    public void removePlaylist(String title){
        if(!isPlaylistInCurrentUser(title)) {
            return;
        }
        Playlist p = getCurrentUser().getPlaylist(title);
        getCurrentUser().removePlaylist(title);
        factory.getDAOPlaylist().delete(p);
        factory.getDAOUser().updateProfile(getCurrentUser());
        updateFilterForCurrentPlaylists(filter);
    }

    public void updatePlaylist(Playlist playlist){
        if(!isPlaylistInCurrentUser(playlist.getTitle()) || playlist.getId() == 0) {
            throw new IllegalArgumentException("Invalid playlist or not in current user");
        }
        getCurrentUser().addOrReplacePlaylist(playlist);
        factory.getDAOPlaylist().updateProfile(playlist);
        updateFilterForCurrentPlaylists(filter);
    }
    
    public Playlist getPlaylist(String title) {
    	return getCurrentUser().getPlaylist(title);
    }

    public String encodePassword(String password) {
        //System.out.println("Encoding: " + password + " -> " + DigestUtils.md5Hex(password));
        String encodedPass = password + "NonEncoded";
        try {
            encodedPass = DigestUtils.md5Hex(password);
        } catch (NullPointerException ignored) {
        }
        return encodedPass;
    }

    public boolean checkPassword(String pass, String encodedPass) {
        return encodePassword(pass).equals(encodedPass);
    }

    public void applyFilter(IFilter filter) {
        getCurrentUser().setFilter(filter);
        factory.getDAOUser().updateProfile(getCurrentUser());

        videoRepository.updateFilteredVideoSet(videoRepository.getVideoList().stream().parallel().filter(v->filter.test(v,getCurrentUser())).collect(Collectors.toSet()));
        updateFilterForCurrentPlaylists(filter);
    }

    private void updateFilterForCurrentPlaylists(IFilter filter){
        this.currentPlaylists = getCurrentUser().getListOfPlaylist().stream().parallel()
                .filter(playlist -> videoRepository.getUnmodifiableFilteredVideoSet().containsAll(playlist.getListOfVideos()))
                .collect(Collectors.toList());

        notifieFilteredVideoChanged();
    }

    public List<Video> getFilteredVideoList(){
        return new ArrayList<>(videoRepository.getUnmodifiableFilteredVideoSet());
    }

	public List<Playlist> getCurrentPlaylists() {
		return currentPlaylists.stream().sorted(Playlist::compareTo).collect(Collectors.toList());
	}

	public void setCurrentPlaylists(List<Playlist> currentPlaylists) {
		this.currentPlaylists = currentPlaylists;
	}

	public void changeMail(String mail) {
        getCurrentUser().setMail(mail);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void changeUsername(String username) {
        getCurrentUser().setUsername(username);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void changePassword(String password) {
        getCurrentUser().setPassword(encodePassword(password));

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void becomePremium() {
        getCurrentUser().setPremium("si");

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void quitPremium() {
        getCurrentUser().setPremium("no");

        applyFilter(new NoFilter());

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }
    public boolean getNightMode() {
        return getCurrentUser().isNightMode();

    }

    public void setNightMode(boolean nightMode) {
        getCurrentUser().setNightMode(nightMode);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }
    private void notifieRecentVideoChanged(){
        recentVideoChangedListeners.forEach(Runnable::run);
    }
    public void subscribeRecentVideoChanged(Runnable callback){
        if (callback == null)
            return;
        recentVideoChangedListeners.add(callback);
    }
    public void unsubscribeRecentVideoChanged(Runnable callback){
        recentVideoChangedListeners.remove(callback);
    }

    public List<Video> getCurrentUserRencentVideo(){
        return Collections.unmodifiableList(getCurrentUser().getRecentVideos());
    }

    public void incrementVideoViewAndAddToRecent(Video v){
        v.incrementViews();
        factory.getDAOVideo().updateProfile(v);
        if (getCurrentUser() == null) // Allow playing video without user, testing only
            return;
        getCurrentUser().addRecentVideo(v);
        factory.getDAOUser().updateProfile(getCurrentUser());
        notifieRecentVideoChanged();
    }

    public Set<Label> getExistingLabelSet(){
        return Label.getExistingLabelSet();
    }


    public List<Video> xmlVideoAdapter(umu.tds.componente.Videos videos) {
        return videos.getVideo().stream()
                .map(v -> {Video video = new Video(v.getTitulo(), v.getURL());
                    video.setLabels(v.getEtiqueta().stream()
                            .map(Label::valueOf).collect(Collectors.toSet()));
                    return video;
                }).collect(Collectors.toList());
    }

    public String generatePDF(String absolutePath) throws FileNotFoundException, MalformedURLException {
        String filename = FilenameUtils.getFullPath(absolutePath)+FilenameUtils.getBaseName(absolutePath)+".pdf";
        List<String> playlistsTitles = getCurrentUser().getListOfPlaylist().stream()
                .map(Playlist::getTitle)
                .collect(Collectors.toList());

        PdfWriter writer = new PdfWriter(filename);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        Image image = new Image(ImageDataFactory.create(Objects.requireNonNull(AppVideo.class.getResource("/images/multimediavideoplayer_128px.png"))));
        document.add(new Paragraph("AppVideo, generacion de playlists y sus videos:"));
        if(!(playlistsTitles.size()>0)) {
            document.add(new Paragraph("No hay playlists creadas"));
            document.close();
            return filename;
        }
        document.add(image);
        // Create a List
        com.itextpdf.layout.element.List list;

        for(String title : playlistsTitles){
        	list = new com.itextpdf.layout.element.List();
    		// Add ListItem objects
            list.add(new ListItem(title));
    		// Add the list
    		document.add(list);

            Table table = new Table(new float[]{4, 4, 4});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("Titulo")));
            table.addHeaderCell(new Cell().add(new Paragraph("URL")));
            table.addHeaderCell(new Cell().add(new Paragraph("Views")));

            getPlaylist(title).getListOfVideos().forEach(video ->{
                table.addCell(new Cell().add(new Paragraph(video.getTitle())));
                table.addCell(new Cell().add(new Paragraph(video.getUrl())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(video.getViews()))));
            });

            document.add(table);
        }
        document.close();
        return filename;
   }
}
