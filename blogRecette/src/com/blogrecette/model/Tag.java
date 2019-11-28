package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Pierre-Henry Barge
 *
 */
@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "auteur")
	private String auteur;
	/**
	 * @param nom
	 */
	@ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
	private Collection<Recette> recettes;
	public Tag(String nom, String auteur) {
		super();
		this.nom = nom;
		this.auteur = auteur;
		this.recettes = new ArrayList<Recette>();
	}

	public Tag() {
		super();	
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

	public Collection<Recette> addRecette(Recette recette) {
		recettes.add(recette);
		return recettes;
	}
	public Collection<Recette> DeleteRecette(Recette recette) {
		recettes.remove(recette);
		return recettes;
	}
	public Collection<Recette> getRecette() {
		return recettes;
	}


}
