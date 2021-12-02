package model;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserRepository {

    private DAOUser userAdapter;
    //private DAOPlaylist playlistAdapter;
    private static UserRepository instance;

    private Map<String, User> userList;  // <Username, User>

    public static UserRepository getInstance(){
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }

    private UserRepository() {
        try {
            userAdapter = DAOFactory.getInstance().getDAOUser();
            //playlistAdapter = factory.getDAOPlaylist();
            this.loadRepository();
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    private void loadRepository() {
        // Function.identity = return the object itself, it's same as e -> e
        userList = userAdapter.getAll().stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
    }

    public User getUser(int id) {
        return userList.values().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public User getUser(String username) {
        return userList.get(username);
    }

    public boolean isUserRegistered(String username){
        return userList.get(username)!=null;
    }

    public void addUser(User user) {
        if(!isUserRegistered(user.getUsername())){
            userList.put(user.getUsername(), user);
        }
    }

    public boolean removeUser(String username) {
        if(isUserRegistered(username)){
            userList.remove(username);
            return true;
        }
        return false;
    }
}
