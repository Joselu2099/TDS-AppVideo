package dao;

import model.User;

import java.util.List;

public interface DAOUser {
    void create(User assistant);

    boolean delete(User assistant);

    void updateProfile(User assistant);

    User get(int id);

    List<User> getAll();
}
