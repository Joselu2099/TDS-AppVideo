package dao;

import beans.Entidad;
import beans.Propiedad;
import model.Label;
import model.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class AppVideoDAOVideo implements DAOVideo {

    //Definimos los atributos de la clase a persistir
    private static final String VIDEO = "Video";
    private static final String TITLE = "Video_Title";
    private static final String URL = "Video_URL";
    private static final String LABELS = "Video_LABELS";
    private static final String VIDEO_VIEWS = "Video_VIEWS";
    private static AppVideoDAOVideo uniqueInstance = null;
    private final ServicioPersistencia servPersistencia;

    private AppVideoDAOVideo() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    // Aplicamos el patron Singleton.
    public static synchronized AppVideoDAOVideo getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new AppVideoDAOVideo();
        return uniqueInstance;
    }

    public Video entityToVideo(Entidad e) {
        Video v = new Video(servPersistencia.recuperarPropiedadEntidad(e, TITLE), servPersistencia.recuperarPropiedadEntidad(e, URL));
        v.setId(e.getId());
        v.setLabels(DAOUtils.splitString(servPersistencia.recuperarPropiedadEntidad(e, LABELS)).stream().map(Label::valueOf).collect(Collectors.toSet()));
        v.setViews(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(e,VIDEO_VIEWS)));
        return v;
    }

    public Entidad videoToEntity(Video v) {
        Entidad e = new Entidad();
        e.setId(v.getId());
        e.setNombre(VIDEO);
        e.setPropiedades(Arrays.asList(
                new Propiedad(TITLE, v.getTitle()),
                new Propiedad(URL, v.getUrl()),
                new Propiedad(VIDEO_VIEWS,String.valueOf(v.getViews())),
                new Propiedad(LABELS, DAOUtils.joinString(v.getLabels().stream().map(Label::name).collect(Collectors.toList())))
        ));

        return e;
    }

    @Override
    public void create(Video video) {
        Entidad e = videoToEntity(video);
        e = servPersistencia.registrarEntidad(e);
        video.setId(e.getId());
    }

    @Override
    public boolean delete(Video video) {
        return servPersistencia.borrarEntidad(servPersistencia.recuperarEntidad(video.getId()));
    }

    @Override
    public void updateProfile(Video v) {
        Entidad e = servPersistencia.recuperarEntidad(v.getId());

        DAOUtils.modifyEntityProperty(e, TITLE, v.getTitle());
        DAOUtils.modifyEntityProperty(e, URL, v.getUrl());
        DAOUtils.modifyEntityProperty(e, VIDEO_VIEWS, String.valueOf(v.getViews()));
        DAOUtils.modifyEntityProperty(e, LABELS, DAOUtils.joinString(v.getLabels().stream().map(Label::name).collect(Collectors.toList())));

        servPersistencia.modificarEntidad(e);

        assert servPersistencia.recuperarEntidad(v.getId()).equals(e);
    }

    @Override
    public Video get(int id) {
        return entityToVideo(servPersistencia.recuperarEntidad(id));
    }


    @Override
    public List<Video> getAll() {
        List<Entidad> v = servPersistencia.recuperarEntidades(VIDEO);
//		return new ArrayList<>();
        return v == null ? new ArrayList<>() : v.stream().map(this::entityToVideo).collect(Collectors.toList());
    }

}
