package dao;

import beans.Entidad;
import beans.Propiedad;
import model.Playlist;
import model.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 */
public final class AppVideoDAOUser implements DAOUser {

    //Definimos los atributos de la clase a persistir
    private static final String USER = "User";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String MAIL = "mail";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DATEOFBIRTH = "dateOfBirth";
    private static final String PREMIUM = "premium";
    private static final String RECENTVIDEOS = "recentVideos";
    private static final String LISTOFPLAYLIST = "listOfPlaylist";
    private static final String FILTER = "filter";
    private static final String NIGHTMODE = "nightMode";
    private static AppVideoDAOUser uniqueInstance = null;
    private final ServicioPersistencia servPersistencia;

    // Se obtiene la instancia del servicio de persistencia
    private AppVideoDAOUser() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    // Aplicamos el patron Singleton.
    public static synchronized AppVideoDAOUser getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new AppVideoDAOUser();
        return uniqueInstance;
    }

    // Funcion que convierte una entidad en un usuario a traves de los atributos que se persistieron
    private User entityToUser(Entidad eUser) {

        String name = servPersistencia.recuperarPropiedadEntidad(eUser, NAME);
        String surname = servPersistencia.recuperarPropiedadEntidad(eUser, SURNAME);
        String mail = servPersistencia.recuperarPropiedadEntidad(eUser, MAIL);
        String username = servPersistencia.recuperarPropiedadEntidad(eUser, USERNAME);
        String password = servPersistencia.recuperarPropiedadEntidad(eUser, PASSWORD);
        String dateOfBirth = servPersistencia.recuperarPropiedadEntidad(eUser, DATEOFBIRTH);
        String premium = servPersistencia.recuperarPropiedadEntidad(eUser, PREMIUM);
        String nightMode = servPersistencia.recuperarPropiedadEntidad(eUser, NIGHTMODE);

        User user = new User(name, surname, mail, username, password, dateOfBirth);
        user.setId(eUser.getId());
        user.setPremium(premium);
        user.setNightMode(Boolean.parseBoolean(nightMode));
        if (servPersistencia.recuperarPropiedadEntidad(eUser, RECENTVIDEOS) != null) {
            List<Integer> idVideos = DAOUtils.stringToList(servPersistencia.recuperarPropiedadEntidad(eUser, RECENTVIDEOS));
            //String to video {idVideo1, idVideo2, idVideo3...} --> [Video1, Video2, Video3]
            user.setRecentVideos(DAOUtils.idsToVideos(idVideos));
        }

        if (servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST) != null) {
            List<Integer> idPlaylists = DAOUtils.stringToList(servPersistencia.recuperarPropiedadEntidad(eUser, LISTOFPLAYLIST));
            Map<Integer, Playlist> playlists = DAOUtils.idsToPlaylists(idPlaylists);
            for (Playlist p : playlists.values()) {
                user.addOrReplacePlaylist(p);
            }
        }

        String filter = servPersistencia.recuperarPropiedadEntidad(eUser, FILTER);
        user.setFilter(DAOUtils.stringToFilter(filter));

        return user;
    }

    // Funcion que convierte un usuario en un entidad extrayendo los atributos de la clase y convirtiendolos en propiedades para persistirlos
    private Entidad userToEntity(User user) {
        Entidad eUser = new Entidad();

        eUser.setNombre(USER);

        eUser.setPropiedades(Arrays.asList(
                new Propiedad(NAME, user.getName()),
                new Propiedad(SURNAME, user.getSurname()),
                new Propiedad(MAIL, user.getMail()),
                new Propiedad(USERNAME, user.getUsername()),
                new Propiedad(PASSWORD, user.getPassword()),
                new Propiedad(DATEOFBIRTH, user.getDateOfBirth()),
                new Propiedad(PREMIUM, user.getPremium()),
                new Propiedad(RECENTVIDEOS, DAOUtils.listToString(DAOUtils.videosToIds(user.getRecentVideos()))),
                new Propiedad(LISTOFPLAYLIST, DAOUtils.listToString(DAOUtils.playlistsToIds(user.getListOfPlaylist()))),
                new Propiedad(FILTER, DAOUtils.filterToString(user.getFilter())),
                new Propiedad(NIGHTMODE, String.valueOf(user.isNightMode()))));
        return eUser;
    }

    // Funcion para extraer el usuario que se pasa como parametro de la base de datos.
    @Override
    public void create(User user) {
        Entidad eUser = this.userToEntity(user);
        eUser = servPersistencia.registrarEntidad(eUser);

        user.setId(eUser.getId());
    }

    @Override
    public boolean delete(User user) {
        Entidad eUser = servPersistencia.recuperarEntidad(user.getId());
        return servPersistencia.borrarEntidad(eUser);
    }

    /**
     * Permite que un Usuario modifique su perfil
     */
    @Override
    public void updateProfile(User user) {
        Entidad eUser = servPersistencia.recuperarEntidad(user.getId());

        DAOUtils.modifyEntityProperty(eUser, NAME, user.getName());
        DAOUtils.modifyEntityProperty(eUser, SURNAME, user.getSurname());
        DAOUtils.modifyEntityProperty(eUser, MAIL, user.getMail());
        DAOUtils.modifyEntityProperty(eUser, USERNAME, user.getUsername());
        DAOUtils.modifyEntityProperty(eUser, PASSWORD, user.getPassword());
        DAOUtils.modifyEntityProperty(eUser, DATEOFBIRTH, user.getDateOfBirth());
        DAOUtils.modifyEntityProperty(eUser, PREMIUM, user.getPremium());
        DAOUtils.modifyEntityProperty(eUser, RECENTVIDEOS, DAOUtils.listToString(DAOUtils.videosToIds(user.getRecentVideos())));
        DAOUtils.modifyEntityProperty(eUser, LISTOFPLAYLIST, DAOUtils.listToString(DAOUtils.playlistsToIds(user.getListOfPlaylist())));
        DAOUtils.modifyEntityProperty(eUser, FILTER, DAOUtils.filterToString(user.getFilter()));
        DAOUtils.modifyEntityProperty(eUser, NIGHTMODE, String.valueOf(user.isNightMode()));

        servPersistencia.modificarEntidad(eUser);
        // Comprobamos si son iguales.
        assert (servPersistencia.recuperarEntidad(user.getId()).equals(eUser));
    }

    @Override
    public User get(int id) {
        return entityToUser(servPersistencia.recuperarEntidad(id));
    }

    @Override
    public List<User> getAll() {
        List<Entidad> entities = servPersistencia.recuperarEntidades(USER);
        return entities == null ? new ArrayList<>() : entities.stream().map(this::entityToUser).collect(Collectors.toList());
    }
}
