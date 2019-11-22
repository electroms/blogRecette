/**
 * 
 */
package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Pierre-Henry Barge
 *
 */
@Entity
@Table(name = "Membre")
public class Membre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "pseudo")
	private String pseudo;
	@Column(name = "mdp")
	private String mdp;
	@Column(name = "email")
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateIncriptionDate", nullable = false)
	private Date dateInscriptionDate;



	/**
	 * @param nom
	 * @param pseudo
	 * @param mdp
	 * @param email
	 * @param dateInscriptionDate
	 */
	@OneToMany(mappedBy = "membre")
	private Collection<Recette>recettes;
	@OneToMany(mappedBy = "membre")
	private Collection<Commentaire>commentaires;
	public Membre() {
		this.recettes = new ArrayList<Recette>();
	}

	public Membre(int id, String nom, String pseudo, String mdp, String email, Date dateInscriptionDate) {
		super();
		this.id = id;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscriptionDate = dateInscriptionDate;
		this.recettes = new ArrayList<Recette>();
	}
	public Membre(String nom, String pseudo, String mdp, String email, Date dateInscriptionDate) {
		super();

		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscriptionDate = dateInscriptionDate;
		this.recettes = new ArrayList<Recette>();
	}
	/**
	 * @return the nom
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the dateInscriptionDate
	 */
	public Date getDateInscriptionDate() {
		return dateInscriptionDate;
	}
	/**
	 * @param dateInscriptionDate the dateInscriptionDate to set
	 */
	public void setDateInscriptionDate(Date dateInscriptionDate) {
		this.dateInscriptionDate = dateInscriptionDate;
	}

	public void addRecette(Recette recette) {
		this.recettes.add(recette);
	}

	/**
	 * @return the recettes
	 */
	public Collection<Recette> getRecette() {
		return recettes;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setRecette(Collection<Recette> recettes) {
		this.recettes = recettes;
	}
	public void addCommentaire(Commentaire commentaire) {
		this.commentaires.add(commentaire);
	}

	/**
	 * @return the recettes
	 */
	public Collection<Commentaire> getCommentaire() {
		return commentaires;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setCommentaire(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	@Override
	public String toString() {
		return "membre [id=" + id + ", nom=" + nom + ", pseudo=" + pseudo + ", mdp=" + mdp + ", email=" + email
				+ ", dateInscriptionDate=" + dateInscriptionDate + "]";
	}

}
