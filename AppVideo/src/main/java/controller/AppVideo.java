package controller;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;
import dao.DAOVideo;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;
import umu.tds.componente.VideosLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppVideo {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static AppVideo uniqueInstance = null;
    private DAOFactory factory;
    private User currentUser;
    private List<Playlist> currentPlaylists;
    private IFilter filter;
    private VideosLoader videosLoader;

    private VideoRepository videoRepository;
    private UserRepository userRepository;

    private List<Runnable> filteredVideoChangedListeners;

    private AppVideo() {
        try {
            factory = DAOFactory.getInstance();
            videoRepository = VideoRepository.getInstance();
            userRepository = UserRepository.getInstance();
            videosLoader = VideosLoader.getInstance();

            filteredVideoChangedListeners = new ArrayList<>();

            currentPlaylists = new ArrayList<>();

            videosLoader.subscribeNewVideoLoaded( videosXML ->{
                List<Video> newVideo = xmlVideoAdapter(videosXML);
                newVideo.stream().forEach(this::persistVideo);
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
        filter = currentUser.getFilter();
        applyFilter(currentUser.getFilter());
    }

    public void subscribeFilteredVideoChange(Runnable callback){
        if (callback != null)
            filteredVideoChangedListeners.add(callback);
    }

    public void unsubscribeFilteredVideoChange(Runnable callback){
        filteredVideoChangedListeners.remove(callback);
    }

    public boolean login(String username, String password) {
        User user = userRepository.getUser(username);
        if (user != null && checkPassword(password, user.getPassword())) {
            this.setCurrentUser(user);
            applyFilter(user.getFilter());
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
        user.setNightMode(false);
        DAOUser daoUser = factory.getDAOUser();
        daoUser.create(user);
        userRepository.addUser(user);
        return true;
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

    private void notifieFilteredVideoChanged(){
        // Usamos runnable porque AppVideo es un singleton y no hay problema de visibilidad,
        // y no sabemos que dato necesita cuando pasa el evento.
        filteredVideoChangedListeners.stream().forEach(Runnable::run);
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
        videoRepository.addVideo(video,filter.test(video));
        if (filter.test(video)){
            notifieFilteredVideoChanged();
        }
    }

    public boolean isPlaylistInCurrentUser(String title){
        return getCurrentUser().hasPlaylist(title);
    }

    // Factoria
    public Playlist createPlaylist(String title,List<Video> videos){
//        System.out.println("isPlaylistRegistered? -> " + getCurrentUser().isPlaylistRegistered(title));
        if(isPlaylistInCurrentUser(title)) {
            return null;
        }
        Playlist playlist = new Playlist(title);
        if (videos != null)
            playlist.setListOfVideos(videos);
        factory.getDAOPlaylist().create(playlist);
        getCurrentUser().addOrReplacePlaylist(playlist);
        factory.getDAOUser().updateProfile(getCurrentUser());
        return playlist;
    }

    public void removePlaylist(String title){
        if(!isPlaylistInCurrentUser(title)) {
            return;
        }
        Playlist p = getCurrentUser().getPlaylist(title);
        getCurrentUser().removePlaylist(title);
        factory.getDAOPlaylist().delete(p);
        factory.getDAOUser().updateProfile(getCurrentUser());
    }

    public void updatePlaylist(Playlist playlist){
        if(!isPlaylistInCurrentUser(playlist.getTitle()) || playlist.getId() == 0) {
            throw new IllegalArgumentException("Invalid playlist or not in current user");
        }
        getCurrentUser().addOrReplacePlaylist(playlist);
        factory.getDAOPlaylist().updateProfile(playlist);
    }

    public void addVideoToPlaylist(String title, Video video){
        if(!getCurrentUser().hasPlaylist(title)) return;
        getCurrentUser().getPlaylist(title).addVideo(video);
        factory.getDAOUser().updateProfile(getCurrentUser());
    }

    public void removeVideoOfPlaylist(String title, Video video){
        if(!getCurrentUser().hasPlaylist(title)) return;
        getCurrentUser().getPlaylist(title).removeVideo(video);
        factory.getDAOUser().updateProfile(getCurrentUser());
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

        videoRepository.updateFilteredVideoSet(videoRepository.getVideoList().stream().filter(filter::test).collect(Collectors.toSet()));
        this.currentPlaylists = getCurrentUser().getListOfPlaylist().stream()
                .filter(playlist -> videoRepository.getUnmodifiableFilteredVideoSet().containsAll(playlist.getListOfVideos()))
                .collect(Collectors.toList());

        notifieFilteredVideoChanged();
        /*
        currentPlaylists = new ArrayList<Playlist>();
        for(Playlist p:getActualUser().getListOfPlaylist()){
            int contador=0;
            for(Video v: p.getListOfVideos()){
                if(!currentVideos.contains(v)) break;
                contador++;
            }
            if(contador>=p.getListOfVideos().size()) currentPlaylists.add(p);
        }
        */
    }

    public List<Video> getFilteredVideoList(){
        return new ArrayList<>(videoRepository.getUnmodifiableFilteredVideoSet());
    }

	public List<Playlist> getCurrentPlaylists() {
		return Collections.unmodifiableList(currentPlaylists);
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

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void setNightMode(boolean nightMode) {
        getCurrentUser().setNightMode(nightMode);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getCurrentUser());
    }

    public void generatePDF() {

    }


    public List<Video> xmlVideoAdapter(umu.tds.componente.Videos videos) {
        return videos.getVideo().stream()
                .map(v -> {Video video = new Video(v.getTitulo(), v.getURL());
                    video.setLabels(v.getEtiqueta().stream()
                            .map(Label::valueOf).collect(Collectors.toSet()));
                    return video;
                }).collect(Collectors.toList());
    }

}
