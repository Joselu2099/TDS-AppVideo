package dao;

import java.util.List;
import model.Video;

public interface DAOVideo {
	public void create(Video assistant);
	public boolean delete(Video assistant);
	public void updateProfile(Video assistant);
	public Video get(int id);
	public List<Video> getAll();
}
