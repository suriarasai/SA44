package club.dao.mysql.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import club.dao.MemberDAO;
import club.exception.NotFoundException;
import club.model.Member;

/**
 * Member Data Access Object (DAO). This class contains all database handling
 * that is needed to permanently store and retrieve Member object instances.
 */

public class MemberDAOImpl implements MemberDAO {

	private Connection conn = null;

	public MemberDAOImpl() throws ClassNotFoundException, SQLException {
		super();
		conn = DBUtil.getConnection();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#createValueObject()
	 */
	public Member createValueObject() {
		return new Member();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#getObject(java.sql.Connection, int)
	 */
	public Member getObject(int memberNumber)
			throws NotFoundException, SQLException {

		Member valueObject = createValueObject();
		valueObject.setMemberNumber(memberNumber);
		load(valueObject);
		return valueObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#load(java.sql.Connection,
	 * club.model.Member)
	 */
	public void load(Member valueObject)
			throws NotFoundException, SQLException {

		String sql = "SELECT * FROM member WHERE (member_number = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getMemberNumber());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#loadAll(java.sql.Connection)
	 */
	public List<Member> loadAll() throws SQLException {

		String sql = "SELECT * FROM member ORDER BY member_number ASC ";
		List<Member> searchResults = listQuery(conn.prepareStatement(sql));

		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#create(java.sql.Connection,
	 * club.model.Member)
	 */
	public synchronized void create(Member valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			sql = "INSERT INTO member ( first_name, second_name, surname) VALUES (?, ?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, valueObject.getFirstName());
			stmt.setString(2, valueObject.getSecondName());
			stmt.setString(3, valueObject.getSurname());

			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

		/**
		 * The following query will read the automatically generated primary key
		 * back to valueObject. This must be done to make things consistent for
		 * upper layer processing logic.
		 */
		sql = "SELECT last_insert_id()";

		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setMemberNumber((int) result.getLong(1));

			} else {
				// System.out.println("Unable to find primary-key for created object!");
				throw new SQLException(
						"Unable to find primary-key for created object!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#save(java.sql.Connection,
	 * club.model.Member)
	 */
	public void save(Member valueObject)
			throws NotFoundException, SQLException {

		String sql = "UPDATE member SET first_name = ?, second_name = ?, surname = ? WHERE (member_number = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, valueObject.getFirstName());
			stmt.setString(2, valueObject.getSecondName());
			stmt.setString(3, valueObject.getSurname());

			stmt.setInt(4, valueObject.getMemberNumber());

			int rowcount = databaseUpdate( stmt);
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
	 * @see club.dao.mysql.jdbc.MemberDAO#delete(java.sql.Connection,
	 * club.model.Member)
	 */
	public void delete(Member valueObject)
			throws NotFoundException, SQLException {

		String sql = "DELETE FROM member WHERE (member_number = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getMemberNumber());

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
	 * @see club.dao.mysql.jdbc.MemberDAO#deleteAll(java.sql.Connection)
	 */
	public void deleteAll() throws SQLException {

		String sql = "DELETE FROM member";
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
	 * @see club.dao.mysql.jdbc.MemberDAO#countAll(java.sql.Connection)
	 */
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM member";
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
	 * @see club.dao.mysql.jdbc.MemberDAO#searchMatching(java.sql.Connection,
	 * club.model.Member)
	 */
	public List<Member> searchMatching( Member valueObject)
			throws SQLException {

		List<Member> searchResults;

		boolean first = true;
		StringBuffer sql = new StringBuffer("SELECT * FROM member WHERE 1=1 ");

		if (valueObject.getMemberNumber() != 0) {
			if (first) {
				first = false;
			}
			sql.append("AND member_number = ")
					.append(valueObject.getMemberNumber()).append(" ");
		}

		if (valueObject.getFirstName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND first_name LIKE '")
					.append(valueObject.getFirstName()).append("%' ");
		}

		if (valueObject.getSecondName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND second_name LIKE '")
					.append(valueObject.getSecondName()).append("%' ");
		}

		if (valueObject.getSurname() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND surname LIKE '").append(valueObject.getSurname())
					.append("%' ");
		}

		sql.append("ORDER BY member_number ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList<Member>();
		else
			searchResults = listQuery(
					conn.prepareStatement(sql.toString()));

		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see club.dao.mysql.jdbc.MemberDAO#getDaogenVersion()
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
			Member valueObject) throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setMemberNumber(result.getInt("member_number"));
				valueObject.setFirstName(result.getString("first_name"));
				valueObject.setSecondName(result.getString("second_name"));
				valueObject.setSurname(result.getString("surname"));

			} else {
				// System.out.println("Member Object Not Found!");
				throw new NotFoundException("Member Object Not Found!");
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
	protected List<Member> listQuery( PreparedStatement stmt)
			throws SQLException {

		ArrayList<Member> searchResults = new ArrayList<Member>();
		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Member temp = createValueObject();

				temp.setMemberNumber(result.getInt("member_number"));
				temp.setFirstName(result.getString("first_name"));
				temp.setSecondName(result.getString("second_name"));
				temp.setSurname(result.getString("surname"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

		return (List<Member>) searchResults;
	}

}
