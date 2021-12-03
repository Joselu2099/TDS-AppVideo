package model;

import com.formdev.flatlaf.IntelliJTheme;
import launcher.Launcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class User implements Comparable<User> {
    private Map<String, Playlist> listOfPlaylist;
    private int id;
    private String name;
    private String surname;
    private String mail;
    private String username;
    private String password;
    private String dateOfBirth;
    private String premium;
    private List<Video> recentVideos;
    private IFilter filter;
    private boolean isNightMode;

    public User(String name, String surname, String mail, String username, String password, String dateOfBirth) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.premium = "no";
        this.listOfPlaylist = new HashMap<>();
        this.recentVideos = new ArrayList<>(5);
        this.filter = new NoFilter();
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

    public boolean isPremium() {
        return this.premium.equals("si");
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public IFilter getFilter() {
        if(filter == null) return new NoFilter();
        return filter;
    }

    public void setFilter(IFilter filter) {
        if(filter == null) this.filter = new NoFilter();
        this.filter = filter;
    }

    public ArrayList<Playlist> getListOfPlaylist() {
        return new ArrayList<>(listOfPlaylist.values());
    }

    public void addRecent(Video v){
        recentVideos.remove(0);
        recentVideos.add(v);
    }

    public void setListOfPlaylist(ArrayList<Playlist> listOfPlaylist) {
        for (Playlist pl : listOfPlaylist) {
            this.listOfPlaylist.put(pl.getTitle(), pl);
        }
    }

    public boolean isPlaylistRegistered(Playlist playlist){
        return listOfPlaylist.containsValue(playlist);
    }

    public boolean isPlaylistRegistered(String title){
        return listOfPlaylist.containsKey(title);
    }

    public void addPlaylist(Playlist playlist) {
        this.listOfPlaylist.put(playlist.getTitle(), playlist);
    }

    public void removePlaylist(String title){
        this.listOfPlaylist.remove(title);
    }

    public Playlist getPlaylist(String title) {
        return listOfPlaylist.get(title);
    }

    public List<Video> getRecentVideos() {
        return new ArrayList<>(recentVideos);
    }

    public void setRecentVideos(List<Video> recentVideos) {
        this.recentVideos = recentVideos;
    }

    public void addRecentVideo(Video video) {
    	if(recentVideos.size()<5) recentVideos.add(video);
    	else {
    		recentVideos.remove(1);
    		recentVideos.add(video);
    	}
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        this.isNightMode = nightMode;
        if (this.isNightMode) {
            IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
        } else {
            IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getUsername() != null ? getUsername().equals(user.getUsername()) : user.getUsername() == null;
    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", premium='" + premium + '\'' +
                ", listOfPlaylist=" + listOfPlaylist +
                ", recentVideos=" + recentVideos.toString() +
                ", filter=" + filter.getClass().getSimpleName() +
                '}';
    }

    @Override
    public int compareTo(@NotNull User o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}