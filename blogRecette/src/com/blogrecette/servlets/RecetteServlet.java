package com.blogrecette.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Membre;
import com.blogrecette.services.CommentaireService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.RecetteService;


/**
 * Servlet implementation class Recette
 */
//Déclaration du webServlets
@WebServlet(name="Recette",urlPatterns= {"/recette"})
public class RecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	//On creer un constructeur vide
	public RecetteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer le DOGET>
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//On creer un TRY CATCH
		try {

			int idRecette = Integer.parseInt(request.getParameter("id"));

			RecetteService recetteService = new RecetteService();
			IngredientService ingredientService = new IngredientService();
			CommentaireService commentaireService = new CommentaireService();

			//On redirige manuellement vers les différents pojos
			List<com.blogrecette.model.Ingredient> ingredients = ingredientService.getIngredientFromRecette(idRecette);
			List<com.blogrecette.model.Commentaire> commentaires = commentaireService.getCommentairesFromRecette(idRecette);
			com.blogrecette.model.Recette recette = recetteService.getRecetteFromId(idRecette);
			//On creer les setAttributes de RECETTE INGREDIENT et COMMENTAIRE
			request.setAttribute("recette", recette);
			request.setAttribute("ingredients", ingredients);
			request.setAttribute("commentaires", commentaires);

			int noteAverage = commentaireService.getNoteAverageFromRecette(idRecette);
            request.setAttribute("noteAverage", noteAverage);
            
        } catch (Exception e) {
            // TODO: handle exception
        }


		//On redirige vers la page souhaitée 
		this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer un DOPOST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//On intialise l'objet ERREUR vide 
		String erreur = "";
		Date dateCreation = new Date();

		int idRecette = Integer.parseInt(request.getParameter("id"));
		String auteur = request.getParameter("auteur");
		//On creer une boucle pour vérifier la bonne saisie des champs dans le formulaire
		if (auteur.isEmpty()) {
			erreur += "Veuillez renseigner un nom<br>";
		}

		String contenu =request.getParameter("contenu");
		if (contenu.isEmpty()) {
			erreur += "Veuillez saisir un contenu<br>";
		}


		RecetteService rs = new RecetteService();
		com.blogrecette.model.Recette recette = null;

		try {
			recette = rs.getRecetteFromId(idRecette);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		CommentaireService commentaireService = new CommentaireService();
		int note = Integer.parseInt(request.getParameter("note"));

		Commentaire commentaire = new Commentaire(auteur, contenu, note, new Date());
		commentaire.setRecette(recette);

		if (erreur.isEmpty()) {

			//On creer un TRY CATCH
			try {
				HttpSession session = request.getSession();
				Membre membre = (Membre) session.getAttribute("membre");

				commentaire.setMembre(membre);
				commentaire = commentaireService.createCommentaire(commentaire);
				//On appel le DOGET 
				doGet(request, response);
				//On creer un DISPATCHER pour rediriger vers la page souhaitée
				this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//On creer une condition qui redirige vers ERREUR si les champs ne sont pas compléter correctement
		else {

			request.setAttribute("erreur", erreur);
		}

	}
}


