package controller;

import model.User;
import model.UserRepository;

public class AppVideo {

	public static final int MIN_PASSLENGTH = 8;
	private static AppVideo uniqueInstance = null;
	private User actualUser;
	
	private AppVideo(){
		this.actualUser = null;
		/* Iniciamos la factoria para la persistencia
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static AppVideo getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideo();
		return uniqueInstance;
	}
	
	public boolean isUserRegistered(String login) {
		return false;
		//return UserRepository.getInstance().getUser(login) != null;
	}
	
	public boolean login(String username, String password) {
		/**User user = UserRepository.getInstance().getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			this.actualUser = user;
			return true;
		}
		**/
		return false;
	}
	
	public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		return true;
	}
	
}
