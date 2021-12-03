package dao;

import model.Playlist;

import java.util.List;

public interface DAOPlaylist {
    void create(Playlist p);

    boolean delete(Playlist p);

    void updateProfile(Playlist p);

    Playlist get(int id);

    List<Playlist> getAll();
}
