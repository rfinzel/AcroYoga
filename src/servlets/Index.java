package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.EventDAO;
import objects.Event;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String headerText = null;
		boolean loggedIn = false;

		Cookie user = null;

		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("user")) {
					user = c;
				}
			}
		}

		if (user != null)
			loggedIn = true;

		// logik noch in jsps tun
		if (loggedIn) {

			headerText = "Hallo " + user.getValue();
			request.setAttribute("user", user.getValue());

		} else {

			headerText = "AcroYoga";
		}

		request.setAttribute("loggedIn", loggedIn);

		request.setAttribute("headerText", headerText);

		// Eventliste not loggedIn
		EventDAO eDAO = new EventDAO();
		List<Event> eL = eDAO.getAllEvents();

		request.setAttribute("eList", eL);

		// Eventliste loggedIn mit Member 0
		EventDAO eDAOin = new EventDAO();
		List<Event> eLin = eDAOin.getEventsByMember(0);

		request.setAttribute("eList", eLin);

		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/views/Index.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
