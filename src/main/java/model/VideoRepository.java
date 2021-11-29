package model;

import controller.AppVideo;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;
import umu.tds.componente.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VideoRepository implements VideosListListener {
    private static VideoRepository uniqueInstance = null;
    private DAOFactory factory;
    private DAOVideo videoAdapter;

    private Map<Integer, Video> videoListIDs; // <id, Video>
    private Map<String, Video> videoList; // <URL, Video>
//    private Map<String, Video> filteredVideoList; // <URL, Video>
    private List<Video> filteredList;
    private List<Runnable> callbacks;
    
    private VideoRepository() {
        callbacks = new ArrayList<>();
        try {
            factory = DAOFactory.getInstance();
            videoAdapter = factory.getDAOVideo();
            videoList = new HashMap<String, Video>();
            videoListIDs = new HashMap<Integer, Video>();
            filteredList = new ArrayList<Video>();
            callbacks = new ArrayList<Runnable>();
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
        setVideoFilter(new NoFilter());
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

    public List<Video> setVideoFilter(IFilter filter){
        return videoList.values().stream()
                .filter(this::testFilter)
                .collect(Collectors.toList());
        //broadcastFilteredVideoChangeListener();
    }

    public List<Video> getVideos(){
        return new ArrayList<>(videoList.values());
    }

    public List<Video> getFilteredVideos(){
        return Collections.unmodifiableList(filteredList);
    }

    public boolean addVideo(Video video) {
        if (getVideoByURL(video.getUrl()) != null) return false;
        videoAdapter.create(video);
        videoList.put(video.getUrl(),video);
        videoListIDs.put(video.getId(),video);
        if (testFilter(video)){
            filteredList.add(video);
            broadcastFilteredVideoChangeListener();
        }
        return true;
    }


    private boolean testFilter(Video video){
        return AppVideo.getInstance().getActualUser().getFilter().test(video);
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
            filteredList.remove(video);
            broadcastFilteredVideoChangeListener();
        }
    }

    @Override
    public void notifiedChargedVideos(VideosListEvent videosListEvent) {
        System.out.println("Videos have been loaded");
    }
}
