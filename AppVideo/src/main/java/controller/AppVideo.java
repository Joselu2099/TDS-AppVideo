package controller;

import model.User;

public class AppVideo {

	private static AppVideo uniqueInstance = null;
	private User actualUser;
	
	public AppVideo(){
		this.actualUser = null;
		/* Iniciamos la factoria para la persistencia
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static AppVideo getInstancia() {
		if (uniqueInstance == null)
			uniqueInstance = new AppVideo();
		return uniqueInstance;
	}
}
