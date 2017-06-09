package dao;

import java.util.ArrayList;

import model.HeroDTO;
import model.HeroSearchDTO;

public interface HeroDAO {
	public HeroDTO findHero(String heroName) throws DAOException;
    public ArrayList<HeroDTO> findAllHero() throws DAOException;
    public void insertHero(HeroDTO hero) throws DAOException;
    public void updateHero(HeroDTO hero) throws DAOException;
    public void deleteHero(HeroDTO hero) throws DAOException;
    public ArrayList<HeroDTO> findHeroByCriteria(HeroSearchDTO so) throws DAOException;
}
