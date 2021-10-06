package dao;

import java.util.List;
import model.User;

public interface DAOUser {	
	void create(User assistant);
	boolean delete(User assistant);
	void updateProfile(User assistant);
	User get(int id);
	List<User> getAll();
}
