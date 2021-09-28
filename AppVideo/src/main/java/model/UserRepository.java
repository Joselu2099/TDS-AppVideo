package model;

public class UserRepository {
	
	private static UserRepository uniqueInstance = null;
	
	private UserRepository() {
		//cargar repositorio de usuarios
	}
	
	public static UserRepository getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new UserRepository();
		return uniqueInstance;
	}
}
