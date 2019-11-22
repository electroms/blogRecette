/**
 * 
 */
package com.blogrecette.test;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecette.model.Recette;
import com.blogrecette.services.RecetteService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Pierre-Henry Barge
 *
 */
//Déclaration de la classe
public class testRecetteService extends TestCase {
	//Affectation des attributs à null
	private static Connection connection = null;
	private static RecetteService recetteService = null;
	private static Recette recetteTest = null;

	/**
	 * @throws java.lang.Exception
	 */
	//Création d'un constructeur vide
	public testRecetteService() {

	}
	//Création d'un nouveau constructeur
	public testRecetteService(String testName) {
		super(testName);
	}
	//Creation du setup
	@Before
	public void setUp() throws Exception {

		recetteService = new RecetteService();
		if (recetteTest == null) {
			recetteTest = new Recette(11, "tartiflette", "plat de pomme de terre avec du fromage fondu", "tartiflette.jpg", new Date(2019/11/15));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	//Creation du tearDown
	public void tearDown() throws Exception {

		recetteService = null;
	}
	//test de la méthode createRecette
	@Test
	public void testCreateRecette() throws Exception {
		//Créé un jeu de test (Arrange)


		//Action (Act)
		recetteTest = recetteService.createRecette(recetteTest);
		Recette RecetteCree = recetteService.getRecetteFromId(recetteTest.getId());

		//Assert

		assertEquals(recetteTest.getIdCategorie(), RecetteCree.getIdCategorie());
		assertEquals(recetteTest.getTitre(), RecetteCree.getTitre());
		assertEquals(recetteTest.getDescription(), RecetteCree.getDescription());
		assertEquals(recetteTest.getPhoto(), RecetteCree.getPhoto());
		assertEquals(recetteTest.getDateCreation().getDate(), RecetteCree.getDateCreation().getDate());
	}
	//test de la méthode updateRecette
	@Test
	public void testUpdateRecette() throws Exception {
		//Cree jeu de test (Arrange)
		Date newDate = new Date("2019/11/06");



		recetteTest.setIdCategorie(2);
		recetteTest.setTitre("recette titre");
		recetteTest.setDescription("recette description");
		recetteTest.setDateCreation(newDate);

		//Action (Act)

		recetteService.updateRecette(recetteTest);
		Recette recetteUpdate = recetteService.getRecetteFromId(recetteTest.getId());

		//Assert

		assertEquals(recetteUpdate.getIdCategorie(), recetteTest.getIdCategorie());
		assertEquals(recetteUpdate.getTitre(), recetteTest.getTitre());
		assertEquals(recetteUpdate.getDescription(), recetteTest.getDescription());
		assertEquals(recetteUpdate.getDateCreation().getDate(), recetteTest.getDateCreation().getDate());
	}
	//test de la méthode deleteRecette
	@Test
	public void testDeleteRecette() throws Exception {

		recetteService.deleteRecette(recetteTest);
		recetteTest = recetteService.getRecetteFromId(recetteTest.getId());

		assertNull(recetteTest);
	}
	//test de la méthode getAllRecette
	@Test
	public void testGetAllRecette() throws Exception {

		//Action 
		List <Recette> recettes = recetteService.getAll();

		assertEquals(recetteService.getAll().size(), 4);

		//
	}
	//Déclaration de l'ordre d'éxécution des tests
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestRecetteService");

		suite.addTest(new testRecetteService("testCreateRecette"));
		suite.addTest(new testRecetteService("testUpdateRecette"));
		suite.addTest(new testRecetteService("testGetAllRecette"));
		suite.addTest(new testRecetteService("testDeleteRecette"));

		return suite;
	}
}
