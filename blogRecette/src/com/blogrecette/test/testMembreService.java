/**
 * 
 */
package com.blogrecette.test;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.model.Membre;
import com.blogrecette.services.MembreService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Pierre-Henry Barge
 *
 */
//Déclaration de la classe
public class testMembreService extends TestCase {
	//Affectation des attributs à null
	private static Connection connection = null;
	private static MembreService membreService = null;
	private static Membre membreTest = null;
	/**
	 * @throws java.lang.Exception
	 */
	//Création d'un constructeur vide
	public testMembreService() {

	}
	//Création d'un nouveau constructeur
	public testMembreService(String testName) {
		super(testName);
	}

	@Before
	//Creation du setup
	public void setUp() throws Exception {


		membreService = new MembreService();
		if (membreTest == null) { 
			membreTest = new Membre("Pierre-Henry", "PHB", "12345", "mailinpro@gmail.com", new Date());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	//Creation du tearDown
	public void tearDown() throws Exception {

		membreService = null;
	}
	//test de la méthode createMemebre
	@Test
	public void testCreateMembre() throws Exception {
		//Créé un jeu de test (Arrange)
		//Membre membre = new Membre(new Date() , "Pierre-Henry", "PHB", "mailinpro@gmail.com", "12345");

		//Action (Act)
		membreTest = membreService.createMembre(membreTest);
		Membre MembreCree = membreService.getMembreFromId(membreTest.getId());


		//Assert
		assertNotEquals(0,membreTest.getId());
		assertEquals(membreTest.getNom(), MembreCree.getNom());
		assertEquals(membreTest.getPseudo(), MembreCree.getPseudo());
		assertEquals(membreTest.getEmail(), MembreCree.getEmail());
		assertEquals(membreTest.getMdp(), MembreCree.getMdp());
		assertEquals(membreTest.getDateInscriptionDate().getDate(), MembreCree.getDateInscriptionDate().getDate());

	}
	//test de la méthode updateMembre
	@Test
	public void testUpdateMembre() throws Exception {
		//Cree jeu de test (Arrange)
		Date newDate = new Date("2019/11/06");
		//Membre membre = new Membre(newDate , "P-H", "PHB", "mailinpro@gmail.com", "56789");
		//membre.setId(3);
		membreTest.setNom("membre Test update");
		membreTest.setPseudo("membre_test_update");
		membreTest.setMdp("5124");
		membreTest.setEmail("mtest@test.fr");
		membreTest.setDateInscriptionDate(newDate);

		//Action (Act)

		membreService.updateMembre(membreTest);
		Membre membreUpdate = membreService.getMembreFromId(membreTest.getId());
		//Assert
		assertEquals(membreUpdate.getNom(), membreTest.getNom());
		assertEquals(membreUpdate.getPseudo(), membreTest.getPseudo());
		assertEquals(membreUpdate.getMdp(), membreTest.getMdp());
		assertEquals(membreUpdate.getEmail(), membreTest.getEmail());
		assertEquals(membreUpdate.getDateInscriptionDate().getDate(), membreTest.getDateInscriptionDate().getDate());


	}
	//test de la méthode deleteMembre
	@Test
	public void testDeleteMembre() throws Exception {
		//Jeu de test
		//int id = 4;
		//Membre membre = null;

		//membreTest = membreService.getMembreFromId(id);
		membreService.deleteMembre(membreTest);
		membreTest = membreService.getMembreFromId(membreTest.getId());

		assertNull(membreTest);
	}
	//Déclaration de l'ordre d'éxécution des tests
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestMembreService");

		suite.addTest(new testMembreService("testCreateMembre"));
		suite.addTest(new testMembreService("testUpdateMembre"));
		suite.addTest(new testMembreService("testDeleteMembre"));

		return suite;
	}
}
