package model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Comparable<Playlist>{

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

    public boolean isVideoInPlaylist(Video video){
        return listOfVideos.contains(video);
    }

    public void addVideo(Video video){
        if(isVideoInPlaylist(video)) return;
        listOfVideos.add(video);
    }

    public void removeVideo(Video video){
        if(!isVideoInPlaylist(video)) return;
        listOfVideos.remove(video);
    }

    public List<Video> getListOfVideos() {
        return new ArrayList<>(listOfVideos);
    }

    public void setListOfVideos(List<Video> listOfVideos) {
        this.listOfVideos = listOfVideos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;

        Playlist playlist = (Playlist) o;

        if (getId() != playlist.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(playlist.getTitle()) : playlist.getTitle() != null) return false;
        return getListOfVideos() != null ? getListOfVideos().equals(playlist.getListOfVideos()) : playlist.getListOfVideos() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getListOfVideos() != null ? getListOfVideos().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", listOfVideos=" + listOfVideos.toString() +
                '}';
    }

    @Override
    public int compareTo(@NotNull Playlist o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
