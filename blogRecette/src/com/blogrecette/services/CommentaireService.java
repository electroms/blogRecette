/**
 * 
 */
package com.blogrecette.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blogrecette.model.Commentaire;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author Pierre-Henry Barge
 *
 */
//On déclare la classe
public class CommentaireService {
	//On creer un constructeur qui appel connection
	public CommentaireService() {

	}

	//On creer la methode createCommentaire
	public static Commentaire createCommentaire(Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(commentaire);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}

	//On creer la methode deleteCommentaire
	public void deleteCommentaire(Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.delete(commentaire);
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
	//On creer le methode updateCommentaire
	public void updateCommentaire(Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.update(commentaire);
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
	//On creer la methode getCommentaireFromId
	public Commentaire getCommentaireFromId(int id) throws Exception {
		Commentaire commentaire = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			commentaire = session.get(Commentaire.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentaire; 

	}
	//ON creer une methode getCommentaireFromRecette avec une liste de commentaire
	public List<Commentaire> getCommentairesFromRecette(int idRecette) throws Exception {

		List < Commentaire > listCommentaires = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			String hql = "SELECT commentaire from Commentaire commentaire join commentaire.recette recette WHERE recette.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idRecette);

			Object listOfCommentaire = query.getResultList();
			listCommentaires = (List<Commentaire>) listOfCommentaire;


		} catch (Exception e) {

			e.printStackTrace();
		}

		return listCommentaires;
	}
	//On creer une methode getNotAverageFromRecette
	public int getNoteAverageFromRecette(int idRecette) throws Exception {
		 int noteAverage = 0;
	        
	        
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            String avgHql = "SELECT floor(avg(note))  from Commentaire C where C.recette = :id";
	            Query query = session.createQuery(avgHql);
	            query.setInteger("id", idRecette);
	            
	            noteAverage = (Integer) query.uniqueResult();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	        return noteAverage;

	}

}

