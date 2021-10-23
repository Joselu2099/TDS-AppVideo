package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Video {

    private int id;
    private String title;
    private String url;
    private int views;
    private Set<Label> labels;
    public Video(String url){
        // Get title with yt API
        try {
            VideoInfo info = new VideoInfo(url);
            title = info.title;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = 0;
        this.url = url;
        this.labels = new TreeSet<>();
    }

    public Video(String title, String url) {
        this.id = 0;
        this.title = title;
        this.url = url;
        this.labels = new TreeSet<>();
    }

    public int getViews() {return views;}

    public void incrementViews(){views++;}

    public void setViews(int views) {this.views = views;}

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Label> getLabels() {
        return new TreeSet<>(labels);
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public void addLabels(Label l){
        labels.add(l);
    }
    public void removeLabels(Label l){
        labels.remove(l);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;

        Video video = (Video) o;

        if (getId() != video.getId()) return false;
        if (getViews() != video.getViews()) return false;
        if (getTitle() != null ? !getTitle().equals(video.getTitle()) : video.getTitle() != null) return false;
        if (getUrl() != null ? !getUrl().equals(video.getUrl()) : video.getUrl() != null) return false;
        return getLabels() != null ? getLabels().equals(video.getLabels()) : video.getLabels() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + getViews();
        result = 31 * result + (getLabels() != null ? getLabels().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", views=" + views +
                ", labels=" + labels +
                '}';
    }
}
