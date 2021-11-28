package model;

import controller.AppVideo;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;
import umu.tds.componente.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VideoRepository implements VideosListListener {
    private static VideoRepository uniqueInstance = null;
    private DAOFactory factory;
    private DAOVideo videoAdapter;

    private Map<Integer, Video> videoListIDs; // <id, Video>
    private Map<String, Video> videoList; // <URL, Video>
    private Map<String, Video> filteredVideoList; // <URL, Video>

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
        videoList = videoAdapter.getAll().stream().collect(Collectors.toMap(Video::getUrl, Function.identity()));
        filteredVideoList = new HashMap<>();
    }

    public void saveUploadedVideos(List<umu.tds.componente.Video> videos) {
        videos.stream()
                .map(v -> {Video video = new Video(v.getTitulo(), v.getURL());
                    video.setLabels(v.getEtiqueta().stream()
                            .map(Label::valueOf).collect(Collectors.toSet())); //TODO
                    return video;
                })
                .filter(v -> !videoList.containsKey(v.getUrl()))
                .forEach(this::addVideo);
    }

    public boolean isVideoRegistered(String url){
        return videoList.get(url)==null;
    }

    public Video getVideoByID(Integer id) {
        return videoListIDs.get(id);
    }

    public Video getVideoByURL(String url) {
        return videoList.get(url);
    }

    public Video getFilteredVideo(String url) {
        return filteredVideoList.get(url);
    }

    public void setFilteredVideoList(List<Video> filteredVideos){
        this.filteredVideoList = new HashMap<>();
        for(Video v: filteredVideos){
            filteredVideoList.put(v.getUrl(), v);
        }
    }

    public List<Video> getVideos(){
        return new ArrayList<>(videoList.values());
    }

    public List<Video> getFilteredVideos(){
        return new ArrayList<>(filteredVideoList.values());
    }

    public void addVideo(Video video) {
        if(AppVideo.getInstance().persistVideo(video)) videoList.put(video.getUrl(), video);
    }

    public void removeVideo(Video video) {
        videoList.remove(video.getUrl());
    }

    @Override
    public void notifiedChargedVideos(VideosListEvent videosListEvent) {
        System.out.println("Videos have been loaded");
    }
}
