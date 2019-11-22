package com.blogrecette.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.services.MembreService;

/**
 * Servlet implementation class Connexion
 */
//Déclaration du webServlets
@WebServlet(name="login",urlPatterns= {"/login"})
//Déclaration de la servlets
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	//On creer un constructeur vide
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer le DOGET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//On creer un booleen logout pour la déconnexion du site 
		Boolean logout = Boolean.parseBoolean(request.getParameter("logout"));
		//On creer une boucle IF pour la session
		if (logout != null && logout == true) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("acceuil");
		}
		//On creer un ELSE avec un DISPATCHER pour la redirection vers LOGIN
		else {

			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//On creer un DOPOST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//On creer une session
		HttpSession session = request.getSession();
		//On initialise ERREUR à null
		String erreur = "";
		//On creer une boucle IF pour confirmer la bonne saisie des informations de connexion
		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			erreur += "Veuillez renseigner un pseudo<br>";
		}

		String mdp = request.getParameter("mdp");
		if (mdp.isEmpty()) {
			erreur += "Veuillez renseigner un mot de passe<br>";
		}
		if (erreur.isEmpty()) {
			//On creer un TRY CATCH
			try {
				MembreService membreService = new MembreService();
				com.blogrecette.model.Membre membre = membreService.getMembreFromPseudoAndPassword(pseudo, mdp);

				if (membre != null) {
					session.setAttribute("membre",membre);
					response.sendRedirect("acceuil");
				}
				else {
					erreur += "Veuillez renseigner un mot de passe valide";
					request.setAttribute("membre", membre);
					response.sendRedirect("login");
				}


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("erreur", erreur);
			doGet(request, response);
		}
	}

}
