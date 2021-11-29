package controller;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;
import dao.DAOVideo;
import gui.AppVideoWindow;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import umu.tds.componente.VideosList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppVideo {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static AppVideo uniqueInstance = null;
    private DAOFactory factory;
    private User actualUser;
    private AppVideoWindow appVideoWindow;
    private List<Video> currentVideos;
    private List<Playlist> currentPlaylists;
    private VideosList videosList;

    private AppVideo() {
        this.setActualUser(null);
        try {
            factory = DAOFactory.getInstance();
            currentVideos = VideoRepository.getInstance().getFilteredVideos();
            currentPlaylists = actualUser.getListOfPlaylist();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public static AppVideo getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new AppVideo();
        return uniqueInstance;
    }

    public User getActualUser() {
        return actualUser;
    }

    public void setActualUser(User actualUser) {
        this.actualUser = actualUser;
    }

    public boolean login(String username, String password) {
        User user = UserRepository.getInstance().getUser(username);
        if (user != null && checkPassword(password, user.getPassword())) {
            this.setActualUser(user);
            return true;
        }
        return false;
    }

    public void createAppVideoWindow(){
        appVideoWindow = new AppVideoWindow();
        appVideoWindow.showWindow();
    }

    public boolean isUserRegistered(String username) {
        return UserRepository.getInstance().isUserRegistered(username);
    }

    public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
        if (isUserRegistered(username)) return false;
        User user = new User(name, surname, mail, username, encodePassword(password), dateOfBirth);
        user.setNightMode(false);
        DAOUser daoUser = factory.getDAOUser();
        daoUser.create(user);
        UserRepository.getInstance().addUser(user);
        return true;
    }

    public boolean removeUser(String username) {
        if (isUserRegistered(username)){
            DAOUser daoUser = factory.getDAOUser();
            daoUser.delete(UserRepository.getInstance().getUser(username));
        }
        return UserRepository.getInstance().removeUser(username);
    }

    public void loadVideos(String file){
        videosList = new VideosList(file);
        videosList.addVideosListListener(VideoRepository.getInstance());

        VideoRepository.getInstance().saveUploadedVideos(videosList.getVideos());

        videosList.setVideos(videosList.getVideos());
    }
    
    public List<Video> searchVideos(String text,Set<Label> labelSet) {
    	return currentVideos.stream()
				.parallel()
				.filter(v-> v.getTitle().contains(text))
				.filter(v -> {
					if (!labelSet.isEmpty())
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

    public boolean persistVideo(Video video){
        DAOVideo daoVideo = factory.getDAOVideo();
        if(daoVideo.get(video.getId())==null){
            daoVideo.create(video);
           return true;
        }else return false;
        //return VideoRepository.getInstance().addVideo(video);
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
        if (!getActualUser().getFilter().getClass().equals(filter.getClass())) {
            getActualUser().setFilter(filter);
        }

        currentVideos = VideoRepository.getInstance().setVideoFilter(filter);
        currentPlaylists = getActualUser().getListOfPlaylist().stream()
                .filter(playlist -> currentVideos.containsAll(playlist.getListOfVideos()))
                .collect(Collectors.toList());
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

        //appVideoWindow.updateVideosOnPanels(VideoRepository.getInstance().getFilteredVideos());
    }

    public List<Video> getCurrentVideos() {
		return Collections.unmodifiableList(currentVideos);
	}

	public void setCurrentVideos(List<Video> currentVideos) {
		this.currentVideos = currentVideos;
	}

	public List<Playlist> getCurrentPlaylists() {
		return currentPlaylists;
	}

	public void setCurrentPlaylists(List<Playlist> currentPlaylists) {
		this.currentPlaylists = currentPlaylists;
	}

	public void changeMail(String mail) {
        getActualUser().setMail(mail);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void changeUsername(String username) {
        getActualUser().setUsername(username);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void changePassword(String password) {
        getActualUser().setPassword(encodePassword(password));

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void becomePremium() {
        getActualUser().setPremium("si");

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void quitPremium() {
        getActualUser().setPremium("no");

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void setNightMode(boolean nightMode) {
        getActualUser().setNightMode(nightMode);

        DAOUser daoUser = factory.getDAOUser();
        daoUser.updateProfile(getActualUser());
    }

    public void generatePDF() {

    }
}
