package club.dao.mysql.jdbc;

import java.sql.SQLException;

import club.dao.FacilityDAO;
import club.dao.MemberDAO;
import club.dao.PersonDAO;
import club.dao.SimpleDAOFactory;

public class SimpleDAOFactoryImpl extends SimpleDAOFactory {

	@Override
	public PersonDAO getPersonDAO() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return new PersonDAOImpl();
	}

	@Override
	public MemberDAO getMemberDAO() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return new MemberDAOImpl();
	}

	@Override
	public FacilityDAO getFacilityDAO() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return new FacilityDAOImpl();
	}

}
