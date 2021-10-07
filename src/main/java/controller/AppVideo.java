package controller;

import java.util.*;
import java.util.stream.Collectors;

import dao.*;
import model.*;

public class AppVideo {

	public static final int MIN_PASSLENGTH = 8;
	public static AppVideo uniqueInstance = null;
	private DAOFactory factory;
	private DAOPlaylist playlistAdapter;
	private User actualUser;
	
	private AppVideo(){
		this.setActualUser(null);
		//Iniciamos la factoria para la persistencia
		try {
			factory = DAOFactory.getInstance();
			playlistAdapter = factory.getDAOPlaylist();
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
	
	public boolean isUserRegistered(String username) {
		return UserRepository.getInstance().getUser(username) != null;
	}
	
	public boolean login(String username, String password) {
		User user = UserRepository.getInstance().getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			this.setActualUser(user);
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		if(isUserRegistered(username)) return false;
		User user = new User(name, surname, mail, username, password, dateOfBirth);

		DAOUser daoUser = factory.getDAOUser(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		daoUser.create(user);

		daoUser.create(user);

		UserRepository.getInstance().addUser(user);
		return true;
	}

	public void loadVideos(){
		//ArrayList<Video> videoList = new ArrayList<Video>();
		VideoRepository.getInstance(); //Se crea el repositorio de videos, lo que conlleva que se cargen todas los videos.
	}

}
