/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Membre;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author Pierre-Henry Barge
 *
 */
//On déclare la classe
public class MembreService {

	//On creer un constructeur qui appel connection
	public MembreService() {
	}
	//On creer la methode createMembre
	public static Membre createMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.save(membre);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return membre;
	}
	//On creer la methode deleteMembre
	public void deleteMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.delete(membre);
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
	//On creer le methode updateMmebre
	public void updateMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.update(membre);
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
	//On creer la methode getMembreFromId
	public Membre getMembreFromId(int id) throws Exception {
		Transaction transaction = null;
		Membre membre = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			membre = session.get(Membre.class, id);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return membre;
	}
	//ON creer une methode getMembreFromPseudoAndPassword
	public Membre getMembreFromPseudoAndPassword(String pseudo, String mdp) throws Exception {
		Transaction transaction=null;

		Membre membre = new Membre();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			String hql = "from Membre m where  m.pseudo = :pseudo and m.mdp = :mdp";
			Query query = session.createQuery(hql);
			query.setParameter("pseudo", pseudo);

			query.setParameter("mdp", mdp);


			List list = query.getResultList();
			membre = (Membre)list.toArray()[0];
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return membre;
	}
}
