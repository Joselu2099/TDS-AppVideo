package model;

import controller.AppVideo;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VideoRepository {
    private static VideoRepository uniqueInstance = null;

    private DAOVideo videoAdapter;
    private Map<Integer, Video> videoIDMap; // <id, Video>
    private Map<String, Video> videoURLMap; // <URL, Video>
    //private Map<String, Video> filteredVideoList; // <URL, Video>
    private Set<Video> filteredVideoSet;

    private VideoRepository() {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            videoAdapter = factory.getDAOVideo();

            // Function.identity = return the object itself, it's same as e -> e
            videoURLMap = videoAdapter.getAll().stream().collect(Collectors.toMap(Video::getUrl, Function.identity()));
            videoIDMap = videoURLMap.values().stream().collect(Collectors.toMap(Video::getId,Function.identity()));
            filteredVideoSet = new HashSet<>(videoURLMap.values());

        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    public static VideoRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new VideoRepository();
        return uniqueInstance;
    }

    public void updateFilteredVideoSet(Set<Video> filteredVideoSet){
        this.filteredVideoSet.addAll(filteredVideoSet);
        this.filteredVideoSet.retainAll(filteredVideoSet);
        // Create new List to guard?
    }

    public Set<Video> getUnmodifiableFilteredVideoSet(){
        return Collections.unmodifiableSet(filteredVideoSet);
    }

    public List<Video> getVideoList(){
        return new ArrayList<>(videoURLMap.values());
    }

    public Video getVideo(Integer id) {
//    	System.out.println(videoIDMap.toString() + ", INTENTO SACAR -> " + videoIDMap.get(id));
        return videoIDMap.get(id);
    }

    public Video getVideo(String url) {
        return videoURLMap.get(url);
    }

    public void addVideo(Video video,boolean addToFiltered) {
        if (getVideo(video.getUrl()) != null) return;
        videoURLMap.put(video.getUrl(),video);
        videoIDMap.put(video.getId(),video);
        // For filteredList, the controller is the responsible test
        if (addToFiltered){
            filteredVideoSet.add(video);
        }
    }

    public boolean isVideoPresent(String url){
        return videoURLMap.get(url) != null;
    }

    public boolean removeVideo(String url) {
        if (!isVideoPresent(url))
            return false;
        int id = getVideo(url).getId();
        videoURLMap.remove(url);
        videoIDMap.remove(id);

        // For filteredList, we can remove safely.
        filteredVideoSet.remove(getVideo(url));
        return true;
    }
}
