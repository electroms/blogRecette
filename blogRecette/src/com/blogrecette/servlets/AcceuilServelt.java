package com.blogrecette.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrecette.model.Recette;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.RecetteService;

/**
 * Servlet implementation class Acceuil
 */
//Déclaration du webServlets
@WebServlet(name="Acceuil",urlPatterns= {"", "/acceuil"})
//Déclaration de la servlets
public class AcceuilServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	//On creer un constructeur vide
	public AcceuilServelt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer le DOGET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//On creer un try catch
		try {

			RecetteService recetteService = new RecetteService();

			List<Recette> recettes = recetteService.getAll();
			request.setAttribute("recettes", recettes);

			CategorieService categorieService = new CategorieService();
			List<com.blogrecette.model.Categorie> categories = categorieService.getAll();
			request.setAttribute("categories", categories);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//On creer un dispatcher pour rediriger vers la page web souhaitée
		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer un DOPOST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
