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
	private final Map<String, Playlist> listOfPlaylist;
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
		this.filter = new NoFilter();
		this.listOfPlaylist = new HashMap<>();
		this.recentVideos = new ArrayList<>();
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
	
	public boolean isPremium() {
		return this.premium.equals("si");
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (getId() != user.getId()) return false;
		if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
		if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
		if (getMail() != null ? !getMail().equals(user.getMail()) : user.getMail() != null) return false;
		if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
			return false;
		if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
			return false;
		if (getDateOfBirth() != null ? !getDateOfBirth().equals(user.getDateOfBirth()) : user.getDateOfBirth() != null)
			return false;
		if (getPremium() != null ? !getPremium().equals(user.getPremium()) : user.getPremium() != null) return false;
		if (getListOfPlaylist() != null ? !getListOfPlaylist().equals(user.getListOfPlaylist()) : user.getListOfPlaylist() != null)
			return false;
		if (getRecentVideos() != null ? !getRecentVideos().equals(user.getRecentVideos()) : user.getRecentVideos() != null)
			return false;
		return getFilter() != null ? getFilter().equals(user.getFilter()) : user.getFilter() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
		result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
		result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
		result = 31 * result + (getPremium() != null ? getPremium().hashCode() : 0);
		result = 31 * result + (getListOfPlaylist() != null ? getListOfPlaylist().hashCode() : 0);
		result = 31 * result + (getRecentVideos() != null ? getRecentVideos().hashCode() : 0);
		result = 31 * result + (getFilter() != null ? getFilter().hashCode() : 0);
		return result;
	}
}
