package model;

import java.util.ArrayList;

public class User {
	private int id;
	private String name;
	private String surname;
	private String email;
	private String user;
	private String password;
	private String dateOfBirth;
	private String premium;
	private TypeOfFilters filter;
	private ArrayList<Playlist> listOfPlaylist;  
	private ArrayList<Video> recentVideos;
	
	public User(String name, String surname, String email, String user, String password, String dateOfBirth) {
		this.id = 0;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.user = user;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.premium = "no";
		this.listOfPlaylist = new ArrayList<Playlist>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
	
	public TypeOfFilters getFilter() {
		return filter;
	}
	
	public void setFilter(TypeOfFilters filter) {
		this.filter = filter;
	}
	
	public ArrayList<Playlist> getListOfPlaylist() {
		return new ArrayList<>(listOfPlaylist);
	}

	public void setListOfPlaylist(ArrayList<Playlist> listOfPlaylist) {
		this.listOfPlaylist = listOfPlaylist;
	}

	public ArrayList<Video> getRecentVideos() {
		return new ArrayList<>(recentVideos);
	}

	public void setRecentVideos(ArrayList<Video> recentVideos) {
		this.recentVideos = recentVideos;
	}
}
