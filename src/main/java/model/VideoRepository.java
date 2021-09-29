package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOVideo;

public class VideoRepository {
	private static VideoRepository uniqueInstance = null;
	private DAOFactory factory;
	private DAOVideo videoAdapter;

	private Map<Integer, Video> videoList;

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
		videoList = new HashMap<Integer, Video>();
		List<Video> videosFromBD = videoAdapter.getAll();
		for(Video v: videosFromBD){
			videoList.put(v.getId(), v);
		}
	}
	
	public Video getVideo(int id) {
		return videoList.get(id);
	}
	
	public Video getVideo(String title) {
		Video video = null;
		for(Video v: videoList.values()) {
			if(v.getTitle() == title) {
				video = v;
			}
		}
		return video;
	}
	
	public void addVideo(Video video) {
		videoList.put(video.getId(), video);
	}
	
	public void removeVideo(Video video) {
		videoList.remove(video.getId());
	}
}
