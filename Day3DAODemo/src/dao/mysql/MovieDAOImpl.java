package dao.mysql;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import dao.MovieDAO;
import dto.MovieDTO;
import exception.MyDataException;


public class MovieDAOImpl implements MovieDAO {

	private static final String FINDER_ALL_QRY = "SELECT * FROM "+MYSQLConstants.MOVIE_TABLE_NAME;
	private static final String UPDATE_QRY = "UPDATE test.movies SET ";
	private static final String INSERT_QRY = "INSERT INTO sa44.movies (id, name, heroname, avgrating) VALUES (?,?,?,?)";
			

	private Connection openConnection() {
		try {
			Class.forName(MYSQLConstants.DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(MYSQLConstants.URL, MYSQLConstants.USER, MYSQLConstants.PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	/* (non-Javadoc)
	 * @see dao.mysql.MovieDAO#findAllMovies()
	 */
	
	public ArrayList<MovieDTO> findAllMovies() throws MyDataException {
		try {
			ArrayList<MovieDTO> result = new ArrayList<MovieDTO>();
			Connection conn = openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(FINDER_ALL_QRY);
			while (rs.next()) {
				MovieDTO m = new MovieDTO();
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setHeroName(rs.getString(3));
				m.setAvgrating(rs.getDouble(4));
				result.add(m);
			}
			stmt.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyDataException("DB Error");

		}
	}

	/* (non-Javadoc)
	 * @see dao.mysql.MovieDAO#updateMovie(dto.MovieDTO)
	 */
	
	public int updateMovie(MovieDTO m) throws MyDataException {
		try {
			Connection conn = openConnection();
			Statement stmt = conn.createStatement();
			String qry = UPDATE_QRY + " ID = " + m.getId() + " ";
			if (m.getName() != null)
				qry += ", name = \'\"" + m.getName()+"\"";
			if (m.getHeroName() != null)
				qry += ", heroname = \"" + m.getHeroName()+"\"";
			if (m.getAvgrating() > 0)
				qry += ", avrrating = " + m.getAvgrating();
			qry += " WHERE ID = " + m.getId();
			int result = stmt.executeUpdate(qry);
			stmt.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyDataException("DB Error");
		}
	}

	
	public int insertMovie(MovieDTO movie) throws MyDataException, SQLException {
		Connection connection = openConnection();
		PreparedStatement statement =  connection.prepareStatement(INSERT_QRY);
		statement.setInt(1, movie.getId());
		statement.setString(2, movie.getName());
		statement.setString(3, movie.getHeroName());
		statement.setDouble(4, movie.getAvgrating());
		int r = statement.executeUpdate(INSERT_QRY);
		return r;
	}

	
	public int deleteMovie(int id) throws MyDataException {
		// TODO Auto-generated method stub
		return 0;
	}


	public MovieDTO findById(int id) throws MyDataException {
		// TODO Auto-generated method stub
		return null;
	}

}
