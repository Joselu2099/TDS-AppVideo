package dao;

import beans.Entidad;
import beans.Propiedad;
import model.Playlist;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class AppVideoDAOPlaylist implements DAOPlaylist {

    //Definimos los atributos de la clase a persistir
    private static final String PLAYLIST = "Playlist";
    private static final String TITLE = "title";
    private static final String LISTOFVIDEOS = "listOfVideos";
    private static AppVideoDAOPlaylist uniqueInstance = null;
    private final ServicioPersistencia servPersistencia;

    private AppVideoDAOPlaylist() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    // Aplicamos el patron Singleton.
    public static AppVideoDAOPlaylist getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new AppVideoDAOPlaylist();
        return uniqueInstance;
    }

    private Playlist entityToPlaylist(Entidad ePlaylist) {
        String title = servPersistencia.recuperarPropiedadEntidad(ePlaylist, TITLE);

        Playlist playlist = new Playlist(title);
        playlist.setId(ePlaylist.getId());

        if (servPersistencia.recuperarPropiedadEntidad(ePlaylist, LISTOFVIDEOS) != null) {
            List<Integer> idVideos = DAOUtils.stringToList(servPersistencia.recuperarPropiedadEntidad(ePlaylist, LISTOFVIDEOS));
            //String to video {idVideo1, idVideo2, idVideo3...} --> [Video1, Video2, Video3]
            playlist.setListOfVideos(DAOUtils.idsToVideos(idVideos));
        }

        return playlist;
    }

    private Entidad playlistToEntity(Playlist playlist) {
        Entidad ePlaylist = new Entidad();

        ePlaylist.setNombre(PLAYLIST);

        ePlaylist.setPropiedades(new ArrayList<>(Arrays.asList(
                new Propiedad(TITLE, playlist.getTitle()),
                new Propiedad(LISTOFVIDEOS, DAOUtils.listToString(DAOUtils.videosToIds(playlist.getListOfVideos()))))));
        return ePlaylist;
    }

    @Override
    public void create(Playlist playlist) {
        Entidad ePlaylist = this.playlistToEntity(playlist);
        ePlaylist = servPersistencia.registrarEntidad(ePlaylist);

        playlist.setId(ePlaylist.getId());
    }

    @Override
    public boolean delete(Playlist playlist) {
        Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());
        return servPersistencia.borrarEntidad(ePlaylist);
    }

    @Override
    public void updateProfile(Playlist playlist) {
        Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());

        DAOUtils.modifyEntityProperty(ePlaylist, TITLE, playlist.getTitle());
        DAOUtils.modifyEntityProperty(ePlaylist, LISTOFVIDEOS, DAOUtils.listToString(DAOUtils.videosToIds(playlist.getListOfVideos())));

        servPersistencia.modificarEntidad(ePlaylist);

        assert ePlaylist.equals(servPersistencia.recuperarEntidad(playlist.getId()));
    }

    @Override
    public Playlist get(int id) {
        Entidad ePlaylist = servPersistencia.recuperarEntidad(id);
        return entityToPlaylist(ePlaylist);
    }

    @Override
    public List<Playlist> getAll() {
        List<Entidad> entities = servPersistencia.recuperarEntidades(PLAYLIST);
        return entities == null ? new ArrayList<>() : entities.stream().map(this::entityToPlaylist).collect(Collectors.toList());
    }

}
