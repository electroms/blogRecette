/**
 * 
 */
package com.blogrecette.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Pierre-Henry Barge
 *
 */
@Entity
@Table(name = "Commentaire")
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "auteur")
	private String auteur;
	@Column(name = "contenu")
	private String contenu;
	@Column(name = "note")
	private int note;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreation", nullable = false)
	private Date dateCreation;

	/**
	 * @param idRecette
	 * @param auteur
	 * @param contenu
	 * @param note
	 * @param dateCreation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Recette recette;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "membre")
	private Membre membre;

	public Commentaire(String auteur, String contenu, int note, Date dateCreation) {
		super();

		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;

	}
	/**
	 * @return the membre
	 */
	public Membre getMembre() {
		return membre;
	}
	/**
	 * @param membre the membre to set
	 */
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public Commentaire() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}
	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	/**
	 * @return the note
	 */
	public int getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(int note) {
		this.note = note;
	}
	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	public Recette getRecette() {
		return this.recette;
	}

	@Override
	public String toString() {
		return "commentaire [id=" + id + ", auteur=" + auteur + ", contenu=" + contenu
				+ ", note=" + note + ", dateCreation=" + dateCreation + "]";
	}


}
