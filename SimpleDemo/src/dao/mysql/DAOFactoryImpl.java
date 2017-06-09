package dao.mysql;

import dao.DAOFactory;
import dao.HeroDAO;

public class DAOFactoryImpl extends DAOFactory {
	private HeroDAO heroDAO = new HeroDAOImpl();
	
    public HeroDAO getHeroDAO() {
        return heroDAO;
    }
    
    
}
