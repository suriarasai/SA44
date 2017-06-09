package dao;



public class DAOFactory {

	public static MovieDAO getMovieDAOInstance() {
		return new dao.mysql.MovieDAOImpl();
	}
	

}
