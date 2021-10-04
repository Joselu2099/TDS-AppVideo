package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOPlaylist;
import dao.DAOUser;
import model.*;

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
		System.out.println(user.getId());
		daoUser.create(user);
		System.out.println(user.getId());


		UserRepository.getInstance().addUser(user);
		return true;
	}

	public void loadVideos(){
		//ArrayList<Video> videoList = new ArrayList<Video>();
		VideoRepository.getInstance(); //Se crea el repositorio de videos, lo que conlleva que se cargen todas los videos.
	}

	public String listToString(List<Integer> ids) {
		return ids != null ? ids.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(";")) : "";
//		StringBuilder result= new StringBuilder();
//		for (Integer i : ids) {
//			result.append(Integer.toString(i)).append(";");
//		}
//		return result.toString();
	}

	public List<Integer> stringToList(String union) {
		return union != null ? Arrays.stream(union.split(";")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()) : new ArrayList<>();
	}
	
	public <K, V> Map<K, V> listsToMap(List<K> keys, List<V> values) {
	    Iterator<K> keyIter = keys.iterator();
	    Iterator<V> valIter = values.iterator();
	    return IntStream.range(0, keys.size()).boxed()
	            .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
	}
	
	public List<Video> idsToVideos(List<Integer> idsVideos) {
		List<Video> videos = new ArrayList<>();
		for(Integer id: idsVideos) {
			videos.add(VideoRepository.getInstance().getVideo(id));
		}
		return videos;
	}
	
	public List<Integer> videosToIds(List<Video> videos) {
		List<Integer> videosIds = new ArrayList<>();
		for(Video v: videos) {
			videosIds.add(v.getId());
		}
		return videosIds;
	}
	
	public Map<Integer, Playlist> idsToPlaylists(List<Integer> idsPlaylists) {
		Map<Integer, Playlist> playlists = new HashMap<Integer, Playlist>();
		for(Integer id : idsPlaylists) {
			playlists.put(id, playlistAdapter.get(id));
		}
		return playlists;
	}
	
	public List<Integer> playlistsToIds(List<Playlist> playlists) {
		List<Integer> playlistsIds = new ArrayList<Integer>();
		for(Playlist p: playlists) {
			playlistsIds.add(p.getId());
		}
		return playlistsIds;
	}
	
	public String FilterToString(IFilter filter) {
		if(filter==null) return "";
		return filter.getClass().getName();
	}
	
	public IFilter stringToFilter(String filterString) {
		if(filterString==null) return null;
		try {
			Class<?> aClass = Class.forName(filterString);
			if (IFilter.class.isAssignableFrom(aClass)){
				return (IFilter) aClass.getConstructor().newInstance();
			}
		} catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
//			e.printStackTrace();
		}
		return null;
	}

}
