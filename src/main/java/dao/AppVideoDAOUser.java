package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import controller.AppVideo;
import model.User;
import model.Video;

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
	
	private ServicioPersistencia servPersistencia;
	
	// Se obtiene la instancia del servicio de persistencia
	private AppVideoDAOUser() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Aplicamos el patrón Singleton.
	// Consiguiendo de esta forma que exista una única instancia de la clase TDSUsuarioDAO
	public static AppVideoDAOUser getInstancia() {
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
			ArrayList<String> idVideos = stringToList(servPersistencia.recuperarPropiedadEntidad(eUser, RECENTVIDEOS));
			//String to video {idVideo1, idVideo2, idVideo3...} --> [Video1, Video2, Video3]
			ArrayList<Video> recentVideos = AppVideo.getInstance().
			user.setRecentVideos(recentVideos);
		}
		
		if (servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST) != null) {
			ArrayList<String> canciones = stringToList(
					servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST));
			for(String id : canciones) {
				usuario.addMasRecientes(id);
			}
		}
		
		return usuario;
	}

	// Función que convierte un usuario en un entidad extrayendo los atributos de la clase y convirtiendolos en propiedades para persistirlos 
	private Entidad usuarioToEntidad(User user) {
		Entidad eUser = new Entidad();
		eUser.setNombre(USER);
		
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, user.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), 
				new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), 
				new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()),
				new Propiedad(LISTAMASESCUCHADAS,listToString(usuario.getMasEscuchadasTitulo())),
				new Propiedad(LISTAMASRECIENTES,listToString(usuario.getMasRecientes())),
				new Propiedad(LISTASREPRODUCCION,listToString(usuario.getIdListasReproduccion())),
				new Propiedad(VISITAS,listToString(usuario.getMasEscuchadasVisitas())),
				new Propiedad(FOTOPERFIL, usuario.getFotoPerfil()),
				
				// Si tiene un descuento se guarda en BD
				new Propiedad(DESCUENTO,
							usuario.getDescuento().isPresent() ? usuario.getDescuento().get().getClass().getName() : ""),
				new Propiedad(PREMIUM, usuario.getPremium()))));
		return eUser;
	}

	
	// Funcion para extraer el usuario que se pasa como parametro de la base de datos.
	public void create(User user) {
		// Si la entidad está registrada no la registra de nuevo
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

	public boolean delete(User user) {
		Entidad eUser;
		eUser = servPersistencia.recuperarEntidad(user.getId());
		
		return servPersistencia.borrarEntidad(eUser);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void updateProfile(User user) {
		Entidad eUser = servPersistencia.recuperarEntidad(user.getId());
		servPersistencia.eliminarPropiedadEntidad(eUser, PASSWORD);
		servPersistencia.anadirPropiedadEntidad(eUser, PASSWORD, user.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUser, MAIL);
		servPersistencia.anadirPropiedadEntidad(eUser, MAIL, user.getMail());
		servPersistencia.eliminarPropiedadEntidad(eUser, PREMIUM);
		servPersistencia.anadirPropiedadEntidad(eUser, PREMIUM, user.getPremium());
		servPersistencia.eliminarPropiedadEntidad(eUser, RECENTVIDEOS);
		servPersistencia.anadirPropiedadEntidad(eUser, RECENTVIDEOS, listToString(user.getRecentVideos()));
		
	}

	public User get(int id) {
		Entidad eUser = servPersistencia.recuperarEntidad(id);
		
		return entidadToUsuario(eUser);
	}

	public List<User> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USER);

		List<User> users = new LinkedList<User>();
		for (Entidad eUser : entidades) {
			users.add(get(eUser.getId()));
		}
		
		return users;
	}
	
	
	private ArrayList<Video> stringToVideos(String videosEnString) {
		return;
	}
	
	private String videosToString(ArrayList<Video> videos) {
		String resultado="";
		for(Video v: videos) {
			String idVideo = Integer.toString(v.getId());
			resultado=resultado+idVideo+";";
		}
		return resultado;
	}
	
	private ArrayList<String> stringToList(String union) {
		ArrayList<String> canciones = new ArrayList<String>();
		if (!union.isEmpty() || union!=null){
		StringTokenizer strTok = new StringTokenizer(union, ";");
		while (strTok.hasMoreTokens()) {
			canciones.add((String) strTok.nextElement());
		}}
		return canciones;
	}
	
	public static <K, V> Map<K, V> listasToMap(List<K> keys, List<V> values) {
	    Iterator<K> keyIter = keys.iterator();
	    Iterator<V> valIter = values.iterator();
	    return IntStream.range(0, keys.size()).boxed()
	            .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
	}
}
