package dao;

/**
 * DAO abstract factory.
 */

public abstract class DAOFactory {
	
	public static final String DAO_TDS = "dao.TDSFactoriaDAO";

	private static DAOFactory uniqueInstance = null;
	
	/** 
	 * Crea un tipo de factoria DAO.
	 * Solo existe el tipo TDSFactoriaDAO
	 */
	@SuppressWarnings("deprecation")
	public static DAOFactory getInstance(String type) throws DAOException{
		if (uniqueInstance == null)
			try { 
				uniqueInstance=(DAOFactory) Class.forName(type).getConstructor().newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
		} 
		return uniqueInstance;
	}
	

	public static DAOFactory getInstance() throws DAOException{
		return getInstance(DAOFactory.DAO_TDS);
	}

	protected DAOFactory (){}
	
	// Metodos factoria para obtener adaptadores

	public abstract DAOUser getDAOUser();
	
	public abstract DAOVideo getDAOVideo();
	
	public abstract DAOPlaylist getDAOPlaylist();
	
}
