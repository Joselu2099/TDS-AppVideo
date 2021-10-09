package controller;

import dao.*;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;

public class AppVideo {

	public static final int MIN_PASSWORD_LENGTH = 8;
	public static AppVideo uniqueInstance = null;
	private DAOFactory factory;
	private User actualUser;
	
	private AppVideo(){
		this.setActualUser(null);
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
		if(user != null && checkPassword(password, user.getPassword())) {
			this.setActualUser(user);
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		if(isUserRegistered(username)) return false;
		User user = new User(name, surname, mail, username, encodePassword(password), dateOfBirth);

		DAOUser daoUser = factory.getDAOUser(); /* DAO Adapter to save the user into Base Data */
		daoUser.create(user);

		UserRepository.getInstance().addUser(user);
		return true;

	}

	public boolean removeUser(String username){
		if(!isUserRegistered(username))
			return false;
		User u = UserRepository.getInstance().getUser(username);
		factory.getDAOUser().delete(u);
		UserRepository.getInstance().removeUser(u);
		return true;
	}

	public static String encodePassword(String password){
		//System.out.println("Encoding: " + password + " -> " + DigestUtils.md5Hex(password));
		return DigestUtils.md5Hex(password);
	}

	public static boolean checkPassword(String pass, String encodedPass){
		return encodePassword(pass).equals(encodedPass);
	}
}
