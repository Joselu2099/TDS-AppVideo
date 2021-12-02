package controller;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;
import dao.DAOVideo;
import gui.AppVideoWindow;
import gui.HomePanel;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;
import umu.tds.componente.VideosList;
import umu.tds.componente.VideosListEvent;
import umu.tds.componente.VideosListListener;
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
    private AppVideoWindow appVideoWindow;
    private List<Playlist> currentPlaylists;
    private IFilter filter;
    private VideosList videosList;

    private VideoRepository videoRepository;
    private UserRepository userRepository;

    private AppVideo() {
        try {
            factory = DAOFactory.getInstance();
            videoRepository = VideoRepository.getInstance();
            userRepository = UserRepository.getInstance();

            currentPlaylists = new ArrayList<>();
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

    public boolean login(String username, String password) {
        User user = userRepository.getUser(username);
        if (user != null && checkPassword(password, user.getPassword())) {
            this.setCurrentUser(user);
            applyFilter(user.getFilter());
            return true;
        }
        return false;
    }

    public void createAppVideoWindow(){
        appVideoWindow = new AppVideoWindow();
        appVideoWindow.showWindow();
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
        videosList.addVideosListListener(new VideosListListener() {
            @Override
            public void notifiedChargedVideos(VideosListEvent videosListEvent) {
                List<umu.tds.componente.Video> videos = videosListEvent.getNewValue();
                //PARSE umu.tds.componente.Video to modelo.Video
                //Add modelo.Video to VideoRepository && persistence
                persistXMLVideoList(videosList.getVideos());
            }
        });
        VideosList videosList = new VideosList(file);
//        videosList.addVideosListListener(AppVideo.getInstance()); // TODO refactor java bean

        persistXMLVideoList(videosList.getVideos());
        updateVideoPreviewPanel();

    }

    private void updateVideoPreviewPanel(){
        if(appVideoWindow==null){
            return;
        }

        HomePanel homePanel = appVideoWindow.getHomePanel();
        homePanel.showVideoPreview(searchVideos(homePanel.getSearchText(),homePanel.getSearchLabelSet()));
        appVideoWindow.getMyPlaylistPanel().setFilteredPlaylist(currentPlaylists);
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

        updateVideoPreviewPanel();
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


    public void persistXMLVideoList(List<umu.tds.componente.Video> videos) {
        videos.stream()
                .map(v -> {Video video = new Video(v.getTitulo(), v.getURL());
                    video.setLabels(v.getEtiqueta().stream()
                            .map(Label::valueOf).collect(Collectors.toSet()));
                    return video;
                })
                .forEach(this::persistVideo);
    }

}
