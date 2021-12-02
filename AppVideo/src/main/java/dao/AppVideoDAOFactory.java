package dao;

/**
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 */

public final class AppVideoDAOFactory extends DAOFactory {

    public AppVideoDAOFactory() {
    }

    @Override
    public AppVideoDAOUser getDAOUser() {
        return AppVideoDAOUser.getInstance();
    }

    @Override
    public AppVideoDAOVideo getDAOVideo() {
        return AppVideoDAOVideo.getInstance();
    }

    @Override
    public AppVideoDAOPlaylist getDAOPlaylist() {
        return AppVideoDAOPlaylist.getInstance();
    }

}
