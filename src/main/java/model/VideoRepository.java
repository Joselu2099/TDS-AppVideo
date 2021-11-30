package model;

import controller.AppVideo;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;
import umu.tds.componente.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VideoRepository {
    private static VideoRepository uniqueInstance = null;
    //private DAOFactory factory;
    private DAOVideo videoAdapter;

    private Map<Integer, Video> videoListIDs; // <id, Video>
    private Map<String, Video> videoList; // <URL, Video>
    //private Map<String, Video> filteredVideoList; // <URL, Video>
    //private List<Video> filteredList;
    //private List<Runnable> callbacks;
    
    private VideoRepository() {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            videoAdapter = factory.getDAOVideo();
            videoList = new HashMap<String, Video>();
            videoListIDs = new HashMap<Integer, Video>();
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
        AppVideo.getInstance().setCurrentVideos(getVideos());

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
        return videoList.get(url)!=null;
    }

    public Video getVideoByID(Integer id) {
    	System.out.println(videoListIDs.toString() + ", INTENTO SACAR -> " + videoListIDs.get(id));
        return videoListIDs.get(id);
    }

    public Video getVideoByURL(String url) {
        return videoList.get(url);
    }
    /*
    private void broadcastFilteredVideoChangeListener(){
        callbacks.stream().forEach(Runnable::run);
    }
    public void addFilteredVideoChangeListener(Runnable callback){
        if (callback != null)
            callbacks.add(callback);
    }
    public void removeFilteredVideoChangeListener(Runnable callback){
        if (callback != null)
            callbacks.remove(callback);
    }
    */
    private boolean testFilter(Video video){
        return AppVideo.getInstance().getActualUser().getFilter().test(video);
    }

    public List<Video> setVideoFilter(IFilter filter){
        return videoList.values().stream()
                .filter(this::testFilter)
                .collect(Collectors.toList());
        //broadcastFilteredVideoChangeListener();
    }

    public List<Video> getVideos(){
        return new ArrayList<>(videoList.values());
    }

    public void addVideo(Video video) {
        if (getVideoByURL(video.getUrl()) != null) return;
        videoAdapter.create(video);
        videoList.put(video.getUrl(),video);
        videoListIDs.put(video.getId(),video);
        if (testFilter(video)){
            AppVideo.getInstance().addCurrentVideo(video);
            //broadcastFilteredVideoChangeListener();
        }
    }

    public boolean isVideoPresent(String url){
        return videoList.get(url) != null;
    }

    public boolean isVideoPresent(int id){
        return videoListIDs.get(id) != null;
    }

    public void removeVideo(Video video) {
        if (isVideoPresent(video.getUrl())){
            videoListIDs.remove(videoList.remove(video.getUrl()).getId());
            if(AppVideo.getInstance().getCurrentVideos().contains(video))
                AppVideo.getInstance().removeCurrentVideo(video);
            //broadcastFilteredVideoChangeListener();
        }
    }
}
