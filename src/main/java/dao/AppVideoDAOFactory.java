package dao;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class AppVideoDAOFactory extends DAOFactory {
	
	public AppVideoDAOFactory() {	}

	@Override
	public AppVideoDAOUser getDAOUser() {	
		return AppVideoDAOUser.getInstancia();
	}
	/*
	@Override
	public TDSCancionDAO getCancionDAO() {
		return TDSCancionDAO.getInstancia();
	}

	@Override
	public ListaReproduccionDAO getListaReproduccionDAO() {
		return TDSListaReproduccionDAO.getInstancia();
	}
	*/
}
