package dao;

import model.Video;

import java.util.List;

public interface DAOVideo {
    void create(Video assistant);

    boolean delete(Video assistant);

    void updateProfile(Video assistant);

    Video get(int id);

    List<Video> getAll();
}
