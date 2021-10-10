package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
		// Function.identity = return the object itself, it's same as e -> e
		videoList = videoAdapter.getAll().stream().collect(Collectors.toMap(Video::getId, Function.identity()));
	}
	
	public Video getVideo(int id) {
		return videoList.get(id);
	}
	
	public Video getVideo(String title) {
		return videoList.values().stream().filter(video -> video.getTitle().equals(title)).findAny().orElse(null);
	}
	
	public void addVideo(Video video) {
		videoList.put(video.getId(), video);
	}
	
	public void removeVideo(Video video) {
		videoList.remove(video.getId());
	}
}
