package club.dao.mysql.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import club.dao.FacilityDAO;
import club.exception.NotFoundException;
import club.model.Facility;

/**
 * Facility Data Access Object (DAO). This class contains all database handling
 * that is needed to permanently store and retrieve Facility object instances.
 */

public class FacilityDAOImpl implements FacilityDAO {

	private Connection conn = null;

	public FacilityDAOImpl() throws ClassNotFoundException, SQLException {
		super();
		conn = DBUtil.getConnection();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#createValueObject()
	 */
	public Facility createValueObject() {
		return new Facility();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#getObject(java.sql.Connection,
	 * java.lang.String)
	 */
	public Facility getObject(String name)
			throws NotFoundException, SQLException {

		Facility valueObject = createValueObject();
		valueObject.setName(name);
		load(valueObject);
		return valueObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#load(java.sql.Connection,
	 * club.model.Facility)
	 */
	public void load(Facility valueObject)
			throws NotFoundException, SQLException {

		if (valueObject.getName() == null) {
			// System.out.println("Can not select without Primary-Key!");
			throw new NotFoundException("Can not select without Primary-Key!");
		}

		String sql = "SELECT * FROM facility WHERE (name = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getName());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#loadAll(java.sql.Connection)
	 */
	public List<Facility> loadAll() throws SQLException {

		String sql = "SELECT * FROM facility ORDER BY name ASC ";
		List<Facility> searchResults = listQuery(conn.prepareStatement(sql));

		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#create(java.sql.Connection,
	 * club.model.Facility)
	 */
	public synchronized void create(Facility valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		// ResultSet result = null;

		try {
			sql = "INSERT INTO facility ( name, description) VALUES (?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, valueObject.getName());
			stmt.setString(2, valueObject.getDescription());

			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#save(java.sql.Connection,
	 * club.model.Facility)
	 */
	public void save(Facility valueObject)
			throws NotFoundException, SQLException {

		String sql = "UPDATE facility SET description = ? WHERE (name = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getDescription());

			stmt.setString(2, valueObject.getName());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#delete(java.sql.Connection,
	 * club.model.Facility)
	 */
	public void delete(Facility valueObject)
			throws NotFoundException, SQLException {

		if (valueObject.getName() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM facility WHERE (name = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getName());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#deleteAll(java.sql.Connection)
	 */
	public void deleteAll() throws SQLException {

		String sql = "DELETE FROM facility";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			int rowcount = databaseUpdate(stmt);
			System.out.println(rowcount);
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#countAll(java.sql.Connection)
	 */
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM facility";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int allRows = 0;

		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next())
				allRows = result.getInt(1);
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
		return allRows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#searchMatching(java.sql.Connection,
	 * club.model.Facility)
	 */
	public List<Facility> searchMatching(Facility valueObject)
			throws SQLException {

		List<Facility> searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM facility WHERE 1=1 ");

		if (valueObject.getName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND name LIKE '").append(valueObject.getName())
					.append("%' ");
		}

		if (valueObject.getDescription() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND description LIKE '")
					.append(valueObject.getDescription()).append("%' ");
		}

		sql.append("ORDER BY name ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList<Facility>();
		else
			searchResults = listQuery(
					conn.prepareStatement(sql.toString()));

		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.FacilityDAO#getDaogenVersion()
	 */
	public String getDaogenVersion() {
		return "DaoGen version 2.4.1";
	}

	/**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected int databaseUpdate(PreparedStatement stmt)
			throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return only one row. The
	 * resultset will be converted to valueObject. If no rows were found,
	 * NotFoundException will be thrown.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 * @param valueObject
	 *            Class-instance where resulting data will be stored.
	 */
	protected void singleQuery(PreparedStatement stmt,
			Facility valueObject) throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setName(result.getString("name"));
				valueObject.setDescription(result.getString("description"));

			} else {
				// System.out.println("Facility Object Not Found!");
				throw new NotFoundException("Facility Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected List<Facility> listQuery(PreparedStatement stmt)
			throws SQLException {

		ArrayList<Facility> searchResults = new ArrayList<Facility>();
		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Facility temp = createValueObject();

				temp.setName(result.getString("name"));
				temp.setDescription(result.getString("description"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

		return (List<Facility>) searchResults;
	}

}
