package dao;

import java.util.List;
import model.Video;

public interface DAOVideo {
	void create(Video assistant);
	boolean delete(Video assistant);
	void updateProfile(Video assistant);
	Video get(int id);
	List<Video> getAll();
}
