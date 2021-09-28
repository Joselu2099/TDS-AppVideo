package model;

import java.util.ArrayList;

public class Playlist {

	private int id;
	private String title;
	private ArrayList<Video> playlist;
	
	public Playlist(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Video> getPlaylist() {
		return new ArrayList<>(playlist);
	}

	public void setPlaylist(ArrayList<Video> playlist) {
		this.playlist = playlist;
	}
	
	
}
