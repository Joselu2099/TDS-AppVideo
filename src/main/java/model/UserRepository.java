package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;

public class UserRepository {
	
	private static UserRepository uniqueInstance = null;
	private DAOFactory factory;
	private DAOUser userAdapter;

	private Map<String, User> userList;  // <Username, User>

	private UserRepository() {
		try {
			factory = DAOFactory.getInstance();
			userAdapter = factory.getDAOUser();
			this.loadRepository();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	public static UserRepository getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new UserRepository();
		return uniqueInstance;
	}

	private void loadRepository() {
		userList = new HashMap<>();
		List<User> usersFromBD = userAdapter.getAll();
		for(User user: usersFromBD){
			userList.put(user.getUsername(), user);
		}
	}

	public User getUser(int id) {
		User user = null;
		for(User u: userList.values()) {
			if(u.getId() == id) {
				user = u;
			}
		}
		return user;
	}

	public User getUser(String username) {
		return userList.get(username);
	}
	
	public void addUser(User user) {
		userList.put(user.getUsername(), user);
	}
	
	public void removeUser(User user) {
		userList.remove(user.getUsername());
	}
}
