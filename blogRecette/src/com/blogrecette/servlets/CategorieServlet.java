package com.blogrecette.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrecette.services.RecetteService;

/**
 * Servlet implementation class Categorie
 */
//Déclaration du webServlets
@WebServlet(name="Categorie",urlPatterns= {"/categorie"})
//Déclaration de la servlets
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	//On creer un constructeur vide
	public CategorieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer le DOGET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//On creer un try catch
		try {

			int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
			RecetteService recetteService = new RecetteService();

			List<com.blogrecette.model.Recette> recettes = recetteService.getRecettesFromCategorie(idCategorie);
			request.setAttribute("recettes", recettes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//On creer un dispatcher pour rediriger vers la page web souhaitée
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer un DOPOST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
