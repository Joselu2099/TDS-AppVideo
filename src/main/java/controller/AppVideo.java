package controller;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;
import model.User;
import model.UserRepository;

public class AppVideo {

	public static final int MIN_PASSLENGTH = 8;
	private static AppVideo uniqueInstance = null;
	private DAOFactory factory;
	private User actualUser;
	
	private AppVideo(){
		this.actualUser = null;
		//Iniciamos la factoria para la persistencia
		try {
			factory = DAOFactory.getInstance();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static AppVideo getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideo();
		return uniqueInstance;
	}
	
	public boolean isUserRegistered(String username) {
		return UserRepository.getInstance().getUser(username) != null;
	}
	
	public boolean login(String username, String password) {
		User user = UserRepository.getInstance().getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			this.actualUser = user;
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		if(isUserRegistered(username)) return false;
		User user = new User(name, surname, mail, username, password, dateOfBirth);

		DAOUser daoUser = factory.getDAOUser(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		daoUser.create(user);

		UserRepository.getInstance().addUser(user);
		return true;
	}
	
}
