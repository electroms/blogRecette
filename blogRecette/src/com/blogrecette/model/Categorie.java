/**
 * 
 */
package com.blogrecette.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Pierre-Henry Barge
 *
 */
@Entity
@Table(name = "Categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	/**
	 * @param nom
	 */
	@OneToMany(mappedBy = "categorie")
	protected Collection<Recette>recettes;

	public Categorie(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public Categorie() {
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
	@Override
	public String toString() {
		return "categorie [id=" + id + ", nom=" + nom + "]";
	}

}
