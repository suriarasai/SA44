package bizlogic;

import java.util.ArrayList;

import model.HeroDTO;
import model.HeroSearchDTO;
import dao.DAOException;
import dao.DAOFactory;
import dao.HeroDAO;

public class HeroManager {
	private HeroDAO heroDAO;
	public HeroManager() {
		heroDAO = DAOFactory.loadInstance().getHeroDAO();
	}
	
	
	
	@SuppressWarnings("finally")
	//public ArrayList<HeroDTO> findHerosByCriteria(String name, String description)
	public ArrayList<HeroDTO> findHerosByCriteria(HeroSearchDTO hso){
		ArrayList<HeroDTO> currentList = new ArrayList<HeroDTO>();
		try {
			currentList = heroDAO.findHeroByCriteria(hso);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			return currentList;
		}
		
	}
	@SuppressWarnings("finally")
	public HeroDTO findHero(String heroName){
		HeroDTO currentHero = new HeroDTO();
		try {
			currentHero = heroDAO.findHero(heroName);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			return currentHero;
		}
		
	}
    @SuppressWarnings("finally")
	public ArrayList<HeroDTO> findAllHero() {
    	ArrayList<HeroDTO> currentList = new ArrayList<HeroDTO>();
		try {
			currentList = heroDAO.findAllHero();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			return currentList;
		}
    }
    public void insertHero(HeroDTO hero) {
    	try {
			heroDAO.insertHero(hero);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void updateHero(HeroDTO hero) {
    	try {
			heroDAO.updateHero(hero);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void deleteHero(HeroDTO hero) {
    	try {
			heroDAO.deleteHero(hero);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
