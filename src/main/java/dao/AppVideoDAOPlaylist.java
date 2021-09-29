package dao;

import java.util.List;

import model.Playlist;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class AppVideoDAOPlaylist implements DAOPlaylist {

	private static AppVideoDAOPlaylist uniqueInstance = null;
	
	//Definimos los atributos de la clase a persistir
	private static final String TITLE = "title";
		
	private ServicioPersistencia servPersistencia;
	
	private AppVideoDAOPlaylist() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Aplicamos el patron Singleton.
	public static AppVideoDAOPlaylist getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideoDAOPlaylist();
		return uniqueInstance;
	}
	
	@Override
	public void create(Playlist assistant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Playlist assistant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateProfile(Playlist assistant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Playlist get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
