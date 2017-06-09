package dao;

public abstract class DAOFactory {
	
	
	
	public abstract HeroDAO getHeroDAO();
	public static DAOFactory loadInstance(){
		 return new dao.mysql.DAOFactoryImpl();
	 }
	
}
