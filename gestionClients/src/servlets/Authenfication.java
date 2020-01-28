package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.FormValidator;

/**
 * Servlet implementation class Authenfication
 */
@WebServlet({ "/login", "/logout" })
public class Authenfication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LOGIN_VIEW = "/WEB-INF/login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authenfication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();

		if (requestedUrl.equals("/login")) {
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
		} else { // deconnexion
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormValidator validator = new FormValidator(request);
	
		boolean result = validator.authentificateUser();
		System.out.println(result);
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("user", request.getParameter("login"));
			response.sendRedirect(request.getContextPath() + "/clients/");
		} else {
			request.setAttribute("login", request.getParameter("login"));
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
		}
	}

}
