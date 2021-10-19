package model;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOUser;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserRepository {

    private static UserRepository uniqueInstance = null;
    private DAOFactory factory;
    private DAOUser userAdapter;
    //private DAOPlaylist playlistAdapter;

    private Map<String, User> userList;  // <Username, User>

    private UserRepository() {
        try {
            factory = DAOFactory.getInstance();
            userAdapter = factory.getDAOUser();
            //playlistAdapter = factory.getDAOPlaylist();
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
        // Function.identity = return the object itself, it's same as e -> e
        userList = userAdapter.getAll().stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
    }

    public User getUser(int id) {
        return userList.values().stream().filter(user -> user.getId() == id).findAny().orElse(null);
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
