package dao;

import java.util.*;
import model.Playlist;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import controller.AppVideo;
import model.User;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class AppVideoDAOUser implements DAOUser {

	private static AppVideoDAOUser uniqueInstance = null;
	
	//Definimos los atributos de la clase a persistir
	private static final String USER = "Usuario";
	private static final String NAME = "nombre";
	private static final String SURNAME = "apellidos";
	private static final String MAIL = "mail";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String DATEOFBIRTH = "dateOfBirth";
	private static final String PREMIUM = "premium";
	private static final String RECENTVIDEOS = "recentVideos";
	private static final String LISTOFPLAYLIST = "listOfPlaylist";
	private static final String FILTER = "filter";
	
	private ServicioPersistencia servPersistencia;
	
	// Se obtiene la instancia del servicio de persistencia
	private AppVideoDAOUser() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Aplicamos el patron Singleton.
	public static AppVideoDAOUser getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideoDAOUser();
		return uniqueInstance;
	}

	// Funcion que convierte una entidad en un usuario a traves de los atributos que se persistieron
	private User entidadToUsuario(Entidad eUser) {

		String name = servPersistencia.recuperarPropiedadEntidad(eUser, NAME);
		String surname = servPersistencia.recuperarPropiedadEntidad(eUser, SURNAME);
		String mail = servPersistencia.recuperarPropiedadEntidad(eUser, MAIL);
		String username = servPersistencia.recuperarPropiedadEntidad(eUser, USERNAME);
		String password = servPersistencia.recuperarPropiedadEntidad(eUser, PASSWORD);
		String dateOfBirth = servPersistencia.recuperarPropiedadEntidad(eUser, DATEOFBIRTH);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUser, PREMIUM);
		
		User user = new User(name, surname, mail, username, password, dateOfBirth);
		user.setId(eUser.getId());
		user.setPremium(premium);
		if (servPersistencia.recuperarPropiedadEntidad(eUser, RECENTVIDEOS) != null) {
			ArrayList<Integer> idVideos = AppVideo.getInstance().stringToList(servPersistencia.recuperarPropiedadEntidad(eUser, RECENTVIDEOS));
			//String to video {idVideo1, idVideo2, idVideo3...} --> [Video1, Video2, Video3]
			user.setRecentVideos(AppVideo.getInstance().idsToVideos(idVideos));
		}
		
		if (servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST) != null) {
			ArrayList<Integer> idPlaylists = AppVideo.getInstance().stringToList(servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST));
			Map<Integer, Playlist> playlists = AppVideo.getInstance().idsToPlaylists(idPlaylists);
			for(Playlist p: playlists.values()) {
				user.addPlaylist(p);
			}
		}
		
		String filter = servPersistencia.recuperarPropiedadEntidad(eUser, FILTER);
		user.setFilter(AppVideo.getInstance().stringToTypeOfFilter(filter));
		
		
		return user;
	}

	// Funcion que convierte un usuario en un entidad extrayendo los atributos de la clase y convirtiendolos en propiedades para persistirlos 
	private Entidad usuarioToEntidad(User user) {
		Entidad eUser = new Entidad();
		
		eUser.setNombre(USER);
		
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NAME, user.getName()),
				new Propiedad(SURNAME, user.getSurname()),
				new Propiedad(MAIL, user.getMail()),
				new Propiedad(USERNAME, user.getUsername()),
				new Propiedad(PASSWORD, user.getPassword()),
				new Propiedad(DATEOFBIRTH, user.getDateOfBirth()),
				new Propiedad(PREMIUM, user.getPremium()),
				new Propiedad(RECENTVIDEOS, AppVideo.getInstance().listToString(AppVideo.getInstance().videosToIds(user.getRecentVideos()))),
				new Propiedad(LISTOFPLAYLIST, AppVideo.getInstance().listToString(AppVideo.getInstance().playlistsToIds(user.getListOfPlaylist()))),
				new Propiedad(FILTER, AppVideo.getInstance().typeOfFilterToString(user.getFilter())))));
		return eUser;
	}

	
	// Funcion para extraer el usuario que se pasa como parametro de la base de datos.
	@Override
	public void create(User user) {
		// Si la entidad estÃ¡ registrada no la registra de nuevo
		boolean existe = true;
		try {
			servPersistencia.recuperarEntidad(user.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return;
		}
		Entidad eUser = this.usuarioToEntidad(user);
		
		eUser = servPersistencia.registrarEntidad(eUser);
		user.setId(eUser.getId());
	}

	@Override
	public boolean delete(User user) {
		Entidad eUser;
		eUser = servPersistencia.recuperarEntidad(user.getId());
		
		return servPersistencia.borrarEntidad(eUser);
	}

	/**
	 * Permite que un Usuario modifique su perfil
	 */
	@Override
	public void updateProfile(User user) {
		Entidad eUser = servPersistencia.recuperarEntidad(user.getId());
		servPersistencia.eliminarPropiedadEntidad(eUser, PASSWORD);
		servPersistencia.anadirPropiedadEntidad(eUser, PASSWORD, user.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUser, MAIL);
		servPersistencia.anadirPropiedadEntidad(eUser, MAIL, user.getMail());
		servPersistencia.eliminarPropiedadEntidad(eUser, PREMIUM);
		servPersistencia.anadirPropiedadEntidad(eUser, PREMIUM, user.getPremium());
		servPersistencia.eliminarPropiedadEntidad(eUser, RECENTVIDEOS);
		servPersistencia.anadirPropiedadEntidad(eUser, RECENTVIDEOS, AppVideo.getInstance().listToString(AppVideo.getInstance().videosToIds(user.getRecentVideos())));
		servPersistencia.eliminarPropiedadEntidad(eUser, LISTOFPLAYLIST);
		servPersistencia.anadirPropiedadEntidad(eUser, LISTOFPLAYLIST, AppVideo.getInstance().listToString(AppVideo.getInstance().playlistsToIds(user.getListOfPlaylist())));
		servPersistencia.eliminarPropiedadEntidad(eUser, FILTER);
		servPersistencia.anadirPropiedadEntidad(eUser, FILTER, AppVideo.getInstance().typeOfFilterToString(user.getFilter()));
	}
	
	@Override
	public User get(int id) {
		Entidad eUser = servPersistencia.recuperarEntidad(id);
		
		return entidadToUsuario(eUser);
	}

	@Override
	public List<User> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USER);

		List<User> users = new LinkedList<User>();
		for (Entidad eUser : entidades) {
			users.add(get(eUser.getId()));
		}
		
		return users;
	}
}
