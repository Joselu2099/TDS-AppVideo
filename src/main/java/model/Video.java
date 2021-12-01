package model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Video implements Comparable<Video>{

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
        this.labels = new TreeSet<>(labels);
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

        return getUrl() != null ? getUrl().equals(video.getUrl()) : video.getUrl() == null;
    }

    @Override
    public int hashCode() {
        return getUrl() != null ? getUrl().hashCode() : 0;
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

    @Override
    public int compareTo(@NotNull Video o) {
        return this.url.compareTo(o.url);
    }
}
