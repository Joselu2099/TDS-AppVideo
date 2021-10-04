package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private int id;
	private String name;
	private String surname;
	private String mail;
	private String username;
	private String password;
	private String dateOfBirth;
	private String premium;
	private Map<String, Playlist> listOfPlaylist;
	private List<Video> recentVideos;
	private IFilter filter;
	
	public User(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		this.id = 0;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.premium = "no";
		this.filter = null;
		this.listOfPlaylist = new HashMap<String, Playlist>();
		this.recentVideos = new ArrayList<Video>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}
	
	public IFilter getFilter() {
		return filter;
	}
	
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}
	
	public ArrayList<Playlist> getListOfPlaylist() {
		return new ArrayList<>(listOfPlaylist.values());
	}

	public void setListOfPlaylist(ArrayList<Playlist> listOfPlaylist) {
		for(Playlist pl: listOfPlaylist) {
			this.listOfPlaylist.put(pl.getTitle(), pl);
		}
	}

	public void addPlaylist(Playlist playlist) {
		this.listOfPlaylist.put(playlist.getTitle(), playlist);
	}
	
	public Playlist getPlaylist(String title) {
		return listOfPlaylist.get(title);
	}
	
	public ArrayList<Video> getRecentVideos() {
		return new ArrayList<>(recentVideos);
	}

	public void setRecentVideos(List<Video> recentVideos) {
		this.recentVideos = recentVideos;
	}
	
	public void addRecentVideo(Video video) {
		this.recentVideos.add(video);
	}
}
