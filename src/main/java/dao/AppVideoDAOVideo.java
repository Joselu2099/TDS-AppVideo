package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import model.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import javax.swing.text.html.parser.Entity;

public final class AppVideoDAOVideo implements DAOVideo{

	private static AppVideoDAOVideo uniqueInstance = null;
	
	//Definimos los atributos de la clase a persistir
	private static final String TYPE = "Video";
	
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

//	public Video fromEntity(Entidad e){
//		Video v = new Video(e.get)
//	}

	@Override
	public List<Video> getAll() {
		List<Entidad> v = servPersistencia.recuperarEntidades(TYPE);
		return new ArrayList<>();
//		return v == null ? new ArrayList<>() : v.stream().map().collect(Collectors.toList());
	}

}
