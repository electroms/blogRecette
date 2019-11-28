/**
 * 
 */
package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.tool.schema.internal.exec.GenerationTarget;

import com.blogrecette.servlets.DeleteTagServlet;

/**
 * @author Pierre-Henry Barge
 *
 */
@Entity
@Table(name = "Recette")
public class Recette {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "idCategorie")
	private int idCategorie;
	@Column(name = "titre")
	private String titre;
	@Column(name = "description")
	private String description;
	@Column(name = "photo")
	private String photo;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreation", nullable = false)
	private Date dateCreation;
	/**
	 * @param idCategorie
	 * @param titre
	 * @param description
	 * @param photo
	 * @param dateCreation
	 */
	@OneToMany(mappedBy = "recette")
	private Collection<Commentaire> commentaires;
	@OneToMany(mappedBy = "recette")
	private Collection<Ingredient> ingredients;
	@ManyToOne
	private Categorie categorie;
	@ManyToOne(fetch = FetchType.LAZY)
	private Membre membre;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Tag> tags;
	public Recette() {
		this.commentaires = new ArrayList<Commentaire>();

	}
	public Recette(int idCategorie, String titre, String description, String photo, Date dateCreation) {
		super();
		this.idCategorie = idCategorie;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.ingredients = new ArrayList<Ingredient>();
		this.commentaires= new ArrayList<Commentaire>();
		this.tags = new ArrayList<Tag>();
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
	 * @return the idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}
	/**
	 * @param idCategorie the idCategorie to set
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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

	public void addCommentaire(Commentaire commentaire) {
		this.commentaires.add(commentaire);
	}
	public Collection<Commentaire> getCommentaire()  {
		return this.commentaires;
	}
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	public Collection<Ingredient> getIngredient() {
		return this.ingredients;
	}
	public Collection<Tag> addTag(Tag tag) {
		tags.add(tag);
		return tags;
	}
	public Collection<Tag> DeleteTag(Tag tag) {
		tags.remove(tag);
		return tags;
	}
	public Collection<Tag> getTag() {
		return tags;
	}

	@Override
	public String toString() {
		return "recette [id=" + id + ", idCategorie=" + idCategorie + ", titre=" + titre + ", description="
				+ description + ", photo=" + photo + ", dateCreation=" + dateCreation + "]";
	}


}
