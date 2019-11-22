/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Categorie;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author Pierre-Henry Barge
 *
 */
//On déclare la classe
public class CategorieService {
	//On creer un constructeur avec l'attribut connection
	public CategorieService() {

	}
	//On creer la méthode create categorie
	public Categorie createCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(categorie);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}

	//On creer une méthode deleteCategorie
	public void deleteCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.delete(categorie);
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
	//On creer la methode updateCategorie
	public void updateCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(categorie);
			session.save(categorie);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//On creer une methode getCategorieFromId
	public Categorie getCategorieFromId(int id) throws Exception {
		Transaction transaction = null;
		Categorie categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			categorie = session.get(Categorie.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}
	//On creer une methode getAll avec un tableau categorie
	public List<Categorie> getAll() throws Exception {
		Transaction transaction = null;
		List < Categorie > listOfCategories = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfCategories = session.createQuery("from Categorie").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return listOfCategories;
	}

}
