package dao;

/**
 * DAO abstract factory.
 */

public abstract class DAOFactory {

    public static final String DAO_TDS = "dao.AppVideoDAOFactory";

    private static DAOFactory uniqueInstance = null;

    protected DAOFactory() {
    }

    /**
     * Crea un tipo de factoria DAO.
     * Solo existe el tipo TDSFactoriaDAO
     */
    @SuppressWarnings("deprecation")
    public static synchronized DAOFactory getInstance(String type) throws DAOException {
        if (uniqueInstance == null)
            try {
                uniqueInstance = (DAOFactory) Class.forName(type).newInstance();
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        return uniqueInstance;
    }

    public static DAOFactory getInstance() throws DAOException {
        return getInstance(DAOFactory.DAO_TDS);
    }

    // Metodos factoria para obtener adaptadores

    public abstract DAOUser getDAOUser();

    public abstract DAOVideo getDAOVideo();

    public abstract DAOPlaylist getDAOPlaylist();

}
