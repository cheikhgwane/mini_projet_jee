package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import dao.ClientDao;
import dao.DaoException;
import utils.FormValidator;

/**
 * Servlet implementation class Clients
 */
@WebServlet({ "/clients/*" })
public class ClientsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADD_VIEW = "/WEB-INF/addClient.jsp";
	private static final String LIST_VIEW = "/WEB-INF/listClient.jsp";
	private static final String MODIFY_VIEW = "/WEB-INF/modifyClient.jsp";

	private static final String HOME = "/WEB-INF/home.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientsManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("clients/add")) {

			getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);

		} else if (requestedUrl.endsWith("clients/list")) {
			try {
				request.setAttribute("clients", ClientDao.getClients());

			} catch (DaoException e) {

				System.out.println(e.getMessage());
			}
			getServletContext().getRequestDispatcher(LIST_VIEW).forward(request, response);

		} else if (requestedUrl.endsWith("clients/modify")) {
			String _id = request.getParameter("idClient");
			
			if(_id==null) {
				response.sendRedirect("add");
				return;
			}
			int idClient = Integer.valueOf(_id);
			System.out.println(_id);
			try {
				ArrayList<Client> list = ClientDao.getClients();
				for (Client c : list) {
					if (c.getIdClient() == idClient) {
						c.setIdClient(idClient);
						request.setAttribute("client", c);	
					}
				}
				getServletContext().getRequestDispatcher(MODIFY_VIEW).forward(request, response);
			} catch (DaoException e) {
				System.out.println(e.getMessage());
			}
		} else if (requestedUrl.endsWith("clients/delete")) {
			String _id = request.getParameter("idClient");
			if(_id==null) {
				getServletContext().getRequestDispatcher(LIST_VIEW).forward(request, response);
			}
			int idClient = Integer.valueOf(_id);
			try {
				ClientDao.deleteClient(idClient);
			} catch (DaoException e) {
				System.out.println(e.getMessage());
			}
			response.sendRedirect("list");
		} else {
			getServletContext().getRequestDispatcher(HOME).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/clients/add")) {

			FormValidator validator = new FormValidator(request);
			boolean result = validator.validateForm();
			
			Client client = validator.getClient();
			if (result) {
				try {
					ClientDao.ajouterClient(client);
				} catch (DaoException e) {
					request.setAttribute("status", "Erreur lors de l'ajout client");
					getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);
					return;
				}
			}
			request.setAttribute("client", new Client());
			request.setAttribute("error", validator.getError());
			request.setAttribute("status", validator.getMessage());
			getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);
		}
		if(requestedUrl.endsWith("/clients/modify")) {
			FormValidator validator = new FormValidator(request);
			boolean result = validator.validateForm();
			
		 String _id = request.getParameter("idClient");
			if(_id==null) {
				System.out.println(_id);
				getServletContext().getRequestDispatcher(LIST_VIEW).forward(request, response);
				return;
			}
			System.out.println(_id);
			if (result) {
				Client client = validator.getClient();
				client.setIdClient(Integer.valueOf(_id));
				try {
					ClientDao.updateClient(client);
					response.sendRedirect("list");
				} catch (DaoException e) {
					System.out.println(e.getMessage());
					request.setAttribute("status", "Erreur lors des modifications");
					getServletContext().getRequestDispatcher(MODIFY_VIEW).forward(request, response);
					return;
				}
			}
		}
	}

}
