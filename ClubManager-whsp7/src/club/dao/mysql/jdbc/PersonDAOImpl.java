package club.dao.mysql.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import club.dao.PersonDAO;
import club.exception.NotFoundException;
import club.model.Person;


/**
 * Person Data Access Object (DAO).
 * This class contains all database handling that is needed to 
 * permanently store and retrieve Person object instances. 
 */

public class PersonDAOImpl implements PersonDAO {
	
	 private Connection conn = null;
	 public PersonDAOImpl() throws ClassNotFoundException, SQLException {
		super();
		conn = DBUtil.getConnection();
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#createValueObject()
	 */
    public Person createValueObject() {
          return new Person();
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#getObject(java.sql.Connection, java.lang.String)
	 */
    public Person getObject(String firstName) throws NotFoundException, SQLException {

          Person valueObject = createValueObject();
          valueObject.setFirstName(firstName);
          load(valueObject);
          return valueObject;
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#load(java.sql.Connection, club.model.Person)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#load(club.model.Person)
	 */
    public void load(Person valueObject) throws NotFoundException, SQLException {

          if (valueObject.getFirstName() == null) {
               //System.out.println("Can not select without Primary-Key!");
               throw new NotFoundException("Can not select without Primary-Key!");
          }

          String sql = "SELECT * FROM person WHERE (first_name = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setString(1, valueObject.getFirstName()); 

               singleQuery(stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#loadAll(java.sql.Connection)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#loadAll()
	 */
    public List<Person> loadAll() throws SQLException {

          String sql = "SELECT * FROM person ORDER BY first_name ASC ";
          List<Person> searchResults = listQuery(conn.prepareStatement(sql));

          return searchResults;
    }



    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#create(java.sql.Connection, club.model.Person)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#create(club.model.Person)
	 */
    public synchronized void create(Person valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
       

          try {
               sql = "INSERT INTO person ( first_name, second_name, sur_name) VALUES (?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setString(1, valueObject.getFirstName()); 
               stmt.setString(2, valueObject.getSecondName()); 
               stmt.setString(3, valueObject.getSurname()); 

               int rowcount = databaseUpdate(stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#save(java.sql.Connection, club.model.Person)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#save(club.model.Person)
	 */
    public void save(Person valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE person SET second_name = ?, sur_name = ? WHERE (first_name = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getSecondName()); 
              stmt.setString(2, valueObject.getSurname()); 

              stmt.setString(3, valueObject.getFirstName()); 

              int rowcount = databaseUpdate(stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#delete(java.sql.Connection, club.model.Person)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#delete(club.model.Person)
	 */
    public void delete(Person valueObject) 
          throws NotFoundException, SQLException {

          if (valueObject.getFirstName() == null) {
               //System.out.println("Can not delete without Primary-Key!");
               throw new NotFoundException("Can not delete without Primary-Key!");
          }

          String sql = "DELETE FROM person WHERE (first_name = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getFirstName()); 

              int rowcount = databaseUpdate(stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#deleteAll(java.sql.Connection)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#deleteAll()
	 */
    public void deleteAll() throws SQLException {

          String sql = "DELETE FROM person";
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


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#countAll(java.sql.Connection)
	 */
    public int countAll() throws SQLException {

          String sql = "SELECT count(*) FROM person";
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


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#searchMatching(java.sql.Connection, club.model.Person)
	 */
    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAOInterface#searchMatching(club.model.Person)
	 */
    public List<Person> searchMatching(Person valueObject) throws SQLException {

          List<Person> searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM person WHERE 1=1 ");

          if (valueObject.getFirstName() != null) {
              if (first) { first = false; }
              sql.append("AND first_name LIKE '").append(valueObject.getFirstName()).append("%' ");
          }

          if (valueObject.getSecondName() != null) {
              if (first) { first = false; }
              sql.append("AND second_name LIKE '").append(valueObject.getSecondName()).append("%' ");
          }

          if (valueObject.getSurname() != null) {
              if (first) { first = false; }
              sql.append("AND sur_name LIKE '").append(valueObject.getSurname()).append("%' ");
          }


          sql.append("ORDER BY first_name ASC ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new ArrayList<Person>();
          else
               searchResults = listQuery(conn.prepareStatement(sql.toString()));

          return searchResults;
    }


    /* (non-Javadoc)
	 * @see club.dao.mysql.jdbc.PersonDAO#getDaogenVersion()
	 */
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }


    /**
     * databaseUpdate-method. This method is a helper method for internal use. It will execute
     * all database handling that will change the information in tables. SELECT queries will
     * not be executed here however. The return value indicates how many rows were affected.
     * This method will also make sure that if cache is used, it will reset when data changes.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }



    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return only one row. The resultset will be converted
     * to valueObject. If no rows were found, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @param valueObject  Class-instance where resulting data will be stored.
     */
    protected void singleQuery(PreparedStatement stmt, Person valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setFirstName(result.getString("first_name")); 
                   valueObject.setSecondName(result.getString("second_name")); 
                   valueObject.setSurname(result.getString("sur_name")); 

              } else {
                    //System.out.println("Person Object Not Found!");
                    throw new NotFoundException("Person Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected List<Person> listQuery(PreparedStatement stmt) throws SQLException {

          ArrayList<Person> searchResults = new ArrayList<Person>();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Person temp = createValueObject();

                   temp.setFirstName(result.getString("first_name")); 
                   temp.setSecondName(result.getString("second_name")); 
                   temp.setSurname(result.getString("sur_name")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List <Person>)searchResults;
    }


}
