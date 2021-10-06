package dao;

import java.util.List;
import model.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class AppVideoDAOVideo implements DAOVideo{

	private static AppVideoDAOVideo uniqueInstance = null;
	
	//Definimos los atributos de la clase a persistir
	private static final String TITLE = "title";
	
	private final ServicioPersistencia servPersistencia;
	
	private AppVideoDAOVideo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Aplicamos el patron Singleton.
	public static AppVideoDAOVideo getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideoDAOVideo();
		return uniqueInstance;
	}
	
	@Override
	public void create(Video assistant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Video assistant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateProfile(Video assistant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Video get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
