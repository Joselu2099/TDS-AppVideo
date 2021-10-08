package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private int id;
	private String title;
	private List<Video> listOfVideos;
	
	public Playlist(String title) {
		this.title = title;
		this.listOfVideos = new ArrayList<>();
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

	public List<Video> getListOfVideos() {
		return new ArrayList<>(listOfVideos);
	}

	public void setListOfVideos(List<Video> listOfVideos) {
		this.listOfVideos = listOfVideos;
	}
	
	
}
