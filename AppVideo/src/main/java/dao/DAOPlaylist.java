package dao;

import model.Playlist;

import java.util.List;

public interface DAOPlaylist {
    void create(Playlist assistant);

    boolean delete(Playlist assistant);

    void updateProfile(Playlist assistant);

    Playlist get(int id);

    List<Playlist> getAll();
}
