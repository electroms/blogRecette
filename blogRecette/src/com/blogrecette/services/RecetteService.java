/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtil;


/**
 * @author Pierre-Henry Barge
 *
 */
//On déclare la classe
public class RecetteService {

	//On creer un constructeur qui appel connection
	public RecetteService() {
	}
	//On creer la methode createRecette
	public Recette createRecette(Recette recette) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.save(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette;
	}
	//On creer la methode deleteRecette
	public void deleteRecette(Recette recette) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//On creer le methode updateRecette
	public void updateRecette(Recette recette) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.update(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//On creer la methode getRecetteFromId
	public Recette getRecetteFromId(int id) throws Exception {
		Transaction transaction = null;
		Recette recette = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			recette = session.get(Recette.class, id);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette; 
	}
	//On creer une methode getAll avec un tableau Recette
	public List<Recette> getAll() throws Exception {
		Transaction transaction = null;
		List < Recette > listOfRecette = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfRecette = session.createQuery("from Recette").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} 

		return listOfRecette;  
	}
	//ON creer une methode getRecettesFromCategorie
	public List<Recette> getRecettesFromCategorie(int idCategorie) throws Exception {
		List < Recette > listOfRecette = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction

			// get an user object
			String hql = "SELECT recette from Recette recette join recette.categorie as categorie WHERE categorie.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idCategorie);

			listOfRecette = (List<Recette>) query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listOfRecette;
	}

}
