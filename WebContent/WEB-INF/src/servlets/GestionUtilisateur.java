package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet(urlPatterns = {"/users/*"})
public class GestionUtilisateur extends HttpServlet
{
	private static final long				serialVersionUID		= 1L;
	private static final String				VUE_AJOUT_UTILISATEUR	= "/WEB-INF/ajoutUtilisateur.jsp";
	private static final String				VUE_LIST_UTILISATEUR	= "/WEB-INF/listeUtilisateur.jsp";

	public static final List<Utilisateur>	utilisateurs			= new ArrayList<Utilisateur>();
	private static final String				VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";
	private static final String AUTH_VIEW = "/WEB-INF/auth.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/users/add"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/users/list"))
		{
			request.setAttribute("utilisateurs", utilisateurs);
			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/users/update"))
		{
			String login = request.getParameter("login");
			for (Utilisateur utilisateur : utilisateurs)
			{
				if (utilisateur.getLogin().equals(login))
				{
					request.setAttribute("utilisateur", utilisateur);
					break;
				}
			}

			getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/users/delete"))
		{
			String login = request.getParameter("login");
			for (Utilisateur utilisateur : utilisateurs)
			{
				if (utilisateur.getLogin().equals(login))
				{
					utilisateurs.remove(utilisateur);
					break;
				}
			}

			response.sendRedirect("list");
		}
		else if(requestedUrl.endsWith("/users/auth")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			if(login.equals("admin") && password.equals("admin")) {
				response.sendRedirect(request.getContextPath());
			}else {
				getServletContext().getRequestDispatcher(AUTH_VIEW)
				.forward(request, response);
				
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/users/add"))
		{
			Utilisateur utilisateur = new Utilisateur(nom, prenom, login,
					password);
			utilisateurs.add(utilisateur);
		}
		else if (requestedUrl.endsWith("/users/update"))
		{
			for (Utilisateur utilisateur : utilisateurs)
			{
				if (utilisateur.getLogin().equals(login))
				{
					utilisateur.setNom(nom);
					utilisateur.setPrenom(prenom);
					utilisateur.setPassword(password);
					break;
				}
			}
		}

		response.sendRedirect(request.getContextPath() + "/users/list");
	}

}
