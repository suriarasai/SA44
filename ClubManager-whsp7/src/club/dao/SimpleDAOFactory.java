package club.dao;

import java.sql.SQLException;

public abstract class SimpleDAOFactory {
	
	public abstract PersonDAO getPersonDAO() throws ClassNotFoundException, SQLException;
	public abstract MemberDAO getMemberDAO() throws ClassNotFoundException, SQLException;
	public abstract FacilityDAO getFacilityDAO() throws ClassNotFoundException, SQLException;
	
	public static SimpleDAOFactory loadInstance(){
		 return new club.dao.mysql.jdbc.SimpleDAOFactoryImpl();
	 }

}
