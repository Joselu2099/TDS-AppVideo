package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOPlaylist;
import dao.DAOUser;
import model.Playlist;
import model.TypeOfFilters;
import model.User;
import model.UserRepository;
import model.Video;
import model.VideoRepository;

public class AppVideo {

	public static final int MIN_PASSLENGTH = 8;
	private static AppVideo uniqueInstance = null;
	private DAOFactory factory;
	private DAOPlaylist playlistAdapter;
	private User actualUser;
	
	private AppVideo(){
		this.setActualUser(null);
		//Iniciamos la factoria para la persistencia
		try {
			factory = DAOFactory.getInstance();
			playlistAdapter = factory.getDAOPlaylist();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static AppVideo getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideo();
		return uniqueInstance;
	}
	
	public User getActualUser() {
		return actualUser;
	}

	public void setActualUser(User actualUser) {
		this.actualUser = actualUser;
	}
	
	public boolean isUserRegistered(String username) {
		return UserRepository.getInstance().getUser(username) != null;
	}
	
	public boolean login(String username, String password) {
		User user = UserRepository.getInstance().getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			this.setActualUser(user);
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(String name, String surname, String mail, String username, String password, String dateOfBirth) {
		if(isUserRegistered(username)) return false;
		User user = new User(name, surname, mail, username, password, dateOfBirth);

		DAOUser daoUser = factory.getDAOUser(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		daoUser.create(user);

		UserRepository.getInstance().addUser(user);
		return true;
	}

	public void loadVideos(){
		ArrayList<Video> videoList = new ArrayList<Video>();
		VideoRepository.getInstance(); //Se crea el repositorio de videos, lo que conlleva que se cargen todas los videos.
	}

	public String listToString(ArrayList<Integer> ids) {
		StringBuilder result= new StringBuilder();
		for (Integer i : ids) {
			result.append(Integer.toString(i)).append(";");
		}
		return result.toString();
	}
	
	public ArrayList<Integer> stringToList(String union) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if (!union.isEmpty() || union!=null){
			StringTokenizer strTok = new StringTokenizer(union, ";");
			while (strTok.hasMoreTokens()) {
				int id = Integer.parseInt((String) strTok.nextElement());
				ids.add(id);
			}
		}
		return ids;
	}
	
	public <K, V> Map<K, V> listsToMap(List<K> keys, List<V> values) {
	    Iterator<K> keyIter = keys.iterator();
	    Iterator<V> valIter = values.iterator();
	    return IntStream.range(0, keys.size()).boxed()
	            .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
	}
	
	public ArrayList<Video> idsToVideos(ArrayList<Integer> idsVideos) {
		ArrayList<Video> videos = new ArrayList<Video>();
		for(Integer id: idsVideos) {
			videos.add(VideoRepository.getInstance().getVideo(id));
		}
		return videos;
	}
	
	public ArrayList<Integer> videosToIds(ArrayList<Video> videos) {
		ArrayList<Integer> videosIds = new ArrayList<Integer>();
		for(Video v: videos) {
			videosIds.add(v.getId());
		}
		return videosIds;
	}
	
	public Map<Integer, Playlist> idsToPlaylists(ArrayList<Integer> idsPlaylists) {
		Map<Integer, Playlist> playlists = new HashMap<Integer, Playlist>();
		for(Integer id : idsPlaylists) {
			playlists.put(id, playlistAdapter.get(id));
		}
		return playlists;
	}
	
	public ArrayList<Integer> playlistsToIds(ArrayList<Playlist> playlists) {
		ArrayList<Integer> playlistsIds = new ArrayList<Integer>();
		for(Playlist p: playlists) {
			playlistsIds.add(p.getId());
		}
		return playlistsIds;
	}
	
	public String typeOfFilterToString(TypeOfFilters filter) {
		switch (filter) {
			case MINORS: {
				return "minors";
			}
			case IMPOPULARS: {
				return "impopulars";
			}
			case MYLISTS: {
				return "mylists";
			}
			default:
				return "none";
		}
	}
	
	public TypeOfFilters stringToTypeOfFilter(String filterString) {
		switch (filterString) {
			case "minors": {
				return TypeOfFilters.MINORS;
			}
			case "impopulars": {
				return TypeOfFilters.IMPOPULARS;
			}
			case "mylists": {
				return TypeOfFilters.MYLISTS;
			}
			default:
				return TypeOfFilters.NONE;
		}
	}
	
}
