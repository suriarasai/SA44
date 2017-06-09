package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.MovieDAO;

import dto.MovieDTO;
import exception.MyDataException;

public class TestDAO {

	public static void main(String[] args) throws SQLException {

		// MovieDAOImpl dao = new MovieDAOImpl();

		MovieDAO dao = DAOFactory.getMovieDAOInstance();
		ArrayList<MovieDTO> mList;
		try {
			mList = dao.findAllMovies();
            System.out.println("before insert query ");
			for (MovieDTO movieDTO : mList) {
				System.out.println(movieDTO.toString());
			}
			MovieDTO movie = new MovieDTO(5, "Matrix", "Keaunu Reaves", 5.0);
			dao.insertMovie(movie);
			mList = dao.findAllMovies();
			System.out.println("afetr insert query ");
			for (MovieDTO movieDTO : mList) {
				System.out.println(movieDTO.toString());
			}

		} catch (MyDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
