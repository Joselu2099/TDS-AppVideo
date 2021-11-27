package model;

import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;
import umu.tds.componente.MapperVideosXMLtoJava;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VideoRepository {
    private static VideoRepository uniqueInstance = null;
    private DAOFactory factory;
    private DAOVideo videoAdapter;

    private Map<Integer, Video> videoList;
    private Map<Integer, Video> filteredVideoList;

    private VideoRepository() {
        try {
            factory = DAOFactory.getInstance();
            videoAdapter = factory.getDAOVideo();
            this.loadRepository();
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    public static VideoRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new VideoRepository();
        return uniqueInstance;
    }

    private void loadRepository() {
        // Function.identity = return the object itself, it's same as e -> e
        videoList = videoAdapter.getAll().stream().collect(Collectors.toMap(Video::getId, Function.identity()));
        filteredVideoList = new HashMap<>();

        //TODO JFileChooser
        String file = "xml/videos.xml";
        loadVideos(file);
    }

    public void loadVideos(String file) {
        Videos videos = MapperVideosXMLtoJava.cargarVideos(file);

        videos.getVideo().stream()
                .map(v -> {Video video = new Video(v.getTitulo(), v.getURL());
                    video.setLabels(v.getEtiqueta().stream()
                            .map(Label::valueOf).collect(Collectors.toSet()));
                    return video;
                })
                .filter(v -> !videoList.containsKey(v.getId()))
                .forEach(this::addVideo);
    }

    public Video getVideo(int id) {
        return videoList.get(id);
    }

    public Video getFilteredVideo(int id) {
        return filteredVideoList.get(id);
    }

    public Video getVideo(String title) {
        return videoList.values().stream().filter(video -> video.getTitle().equals(title)).findAny().orElse(null);
    }

    public Video getFilteredVideo(String title) {
        return filteredVideoList.values().stream().filter(video -> video.getTitle().equals(title)).findAny().orElse(null);
    }

    public void setFilteredVideoList(List<Video> filteredVideos){
        this.filteredVideoList = new HashMap<>();
        for(Video v: filteredVideos){
            filteredVideoList.put(v.getId(), v);
        }
    }

    public List<Video> getVideos(){
        return new ArrayList<>(videoList.values());
    }

    public List<Video> getFilteredVideos(){
        return new ArrayList<>(filteredVideoList.values());
    }

    public void addVideo(Video video) {
        videoList.put(video.getId(), video);
    }

    public void removeVideo(Video video) {
        videoList.remove(video.getId());
    }
}
