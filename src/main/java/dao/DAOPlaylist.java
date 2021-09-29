package dao;

import java.util.List;
import model.Playlist;

public interface DAOPlaylist {
	public void create(Playlist assistant);
	public boolean delete(Playlist assistant);
	public void updateProfile(Playlist assistant);
	public Playlist get(int id);
	public List<Playlist> getAll();
}
