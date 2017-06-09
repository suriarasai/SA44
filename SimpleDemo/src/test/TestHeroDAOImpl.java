package test;

import static org.junit.Assert.*;

import model.HeroDTO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DAOException;
import dao.mysql.HeroDAOImpl;

public class TestHeroDAOImpl {
	
	private HeroDAOImpl heroImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		heroImpl = new HeroDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteHero() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllHero() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindHero() throws DAOException {
	     HeroDTO hero = heroImpl.findHero("Ghandhi");
	     assertTrue(hero.getName().trim().equalsIgnoreCase("Ghandhi"));
	}

	@Test
	public void testInsertHero() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateHero() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindHeroByCriteria() {
		fail("Not yet implemented");
	}

}
