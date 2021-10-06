package dao;

import java.util.List;
import model.Playlist;

public interface DAOPlaylist {
	void create(Playlist assistant);
	boolean delete(Playlist assistant);
	void updateProfile(Playlist assistant);
	Playlist get(int id);
	List<Playlist> getAll();
}
