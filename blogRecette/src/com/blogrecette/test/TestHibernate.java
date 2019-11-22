/**
 * 
 */
package com.blogrecette.test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.sun.xml.internal.txw2.TXW;

class TestHibernate {

	protected Session session;
	protected SessionFactory sessionFactory;
	public static void main(String args[]) throws Exception {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();


		/*Membre membre = new Membre(1, "Pierre-Henry", "PHB", "12345", "toto@toto.com", new Date(2019/11/14));

		session.save(membre);

		Categorie categorie = new Categorie("Plat Principal");
		session.save(categorie);*/

		/*Ingredient ingredient = new Ingredient("poivre", 5, "g");
		session.save(ingredient);*/
		//Transaction tx = null;
		//tx = session.beginTransaction();
		/*Membre membre = new Membre(1, "Pierre-Henry", "PHB", "12345", "toto@toto.com", new Date(2019/11/14));
		session.save(membre);
		Recette recette = new Recette(1, "Tartiflette", "Plat à base de pommes de terre", "tartiflette.jpg", new Date(2019/11/15));
		membre.addRecette(recette);
		session.save(recette);
		session.flush();*/
		/*Ingredient ingredient = new Ingredient("Carotte", 9,"u");
		session.save(ingredient);
		Recette recette = new Recette(2, "Veloute description carotte au cumin", "Entree tras appetissante", "veloute-de-carotte-au-cumin", new Date(2019/11/15));

		recette.addIngredient(ingredient);
		session.save(recette);
		session.flush();*/
		/*Commentaire commentaire = new Commentaire("Pierre-Henry", "Ceci est un commentaire", 4, new Date(2019/11/15));
		session.save(commentaire);*/

		/*Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Membre membre = new Membre(2, "Pierre-Henry", "PHB", "12345", "mailinpro@gmail.com", new Date ("2019/11/13"));
			session.save(membre);
			session.flush();
			tx.commit();

			String hql = "FROM Membre M";
			Query query = session.createQuery(hql);
			List results = query.getResultList();
			System.out.println(results);

			String hql = "FROM Membre M where M.id = 1";
			Query query = session.createQuery(hql);
			List list = query.getResultList();
			Membre membre = (Membre) list.toArray()[0];
			System.out.println(membre);

			String hql = "FROM Membre M WHERE M.dateInscriptionDate = :date";
			Query query = session.createQuery(hql).setParameter("date", new Date());
			List results = query.getResultList();
			System.out.println(results);

			tx = session.beginTransaction();
            Membre membre = (Membre)session.load(Membre.class,1);
            session.delete(membre);
            session.flush() ;

			String sDate1="12/11/2019";
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
			System.out.println(sDate1+"\t"+date);

			String hqlDelete = "DELETE FROM Membre M WHERE M.dateInscriptionDate<=:date";
			Query deleteQuery = session.createQuery(hqlDelete).setParameter("date",date);
			deleteQuery.executeUpdate();

			tx = session.beginTransaction();
			Membre membre = (Membre) session.load(Membre.class, 1);
			membre.setNom("Ahmed");
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}*/ 

		sessionFactory.close();
	}

}
