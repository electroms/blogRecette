/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blogrecette.model.Ingredient;
import com.blogrecette.utils.HibernateUtil;


/**
 * @author Pierre-Henry Barge
 *
 */
//On déclare la classe
public class IngredientService {
	//On creer un constructeur qui appel connectio
	public IngredientService() {

	}
	//On creer le methode createIngredient
	public static Ingredient createIngredient(Ingredient ingredient) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.save(ingredient);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}

	//On creer la methode deleteIngredient
	public void deleteIngredient(Ingredient ingredient) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//On creer le methode updateIngredient
	public void updateIngredient(Ingredient ingredient) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.update(ingredient);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	//On creer le methode getIngredientFromId
	public Ingredient getIngredientFromId(int id) throws Exception {
		Transaction transaction = null;
		Ingredient ingredient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ingredient = session.get(Ingredient.class, id);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}
	//On creer la methode getIngredientFromRecette avec une liste d'ingredients
	public List<Ingredient> getIngredientFromRecette(int idRecette) throws Exception {


		List < Ingredient > listIngredients = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction

			// get an user object
			String hql = "SELECT ingredient from Ingredient ingredient join ingredient.recette as recette WHERE recette.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idRecette);

			Object listOfIngredients = query.getResultList();
			listIngredients = (List<Ingredient>) listOfIngredients;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listIngredients;
	}
}
