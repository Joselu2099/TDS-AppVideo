package dao;

import java.util.List;
import model.User;

public interface DAOUser {	
	public void create(User assistant);
	public boolean delete(User assistant);
	public void updateProfile(User assistant);
	public User get(int id);
	public List<User> getAll();
}
