package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
		String user = null;
		String headerText = null;
		HttpSession session = request.getSession();
		boolean angemeldet = false;

		if (angemeldet) {
			user = "Ren�";
			headerText = "Hallo " + user;
		} else {
			user = "<li class=\"dropdown\">"
					+ "<a class=\"dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\">Sign In <strong class=\"caret\"></strong></a>"
					+ "<div class=\"dropdown-menu\" style=\"padding: 15px;\">"
					+ "<form method=\"post\" action=\"Login\" accept-charset=\"UTF-8\">"
					+ "<input style=\"margin-bottom: 15px;\" type=\"text\" placeholder=\"E-Mail\" id=\"username\" name=\"username\">"
					+ "<input style=\"margin-bottom: 15px;\" type=\"password\" placeholder=\"Password\" id=\"password\" name=\"password\">"
					+ "<p>E-Mail Adresse oder Passwort falsch</p>"
					+ "<input style=\"float: left; margin-right: 10px;\" type=\"checkbox\" name=\"remember-me\" id=\"remember-me\" value=\"1\">"
					+ "<label class=\"string optional\" for=\"user_remember_me\"> Remember me</label>"
					+ "<input class=\"btn btn-primary btn-block\" type=\"submit\" id=\"sign-in\" value=\"Sign In\">"
					+ "</form>" + "</div>" + "</li>";
			headerText = "AcroYoga";
		}

		request.setAttribute("user", user);
		request.setAttribute("headerText", headerText);

		EventDAO eDAO = new EventDAO();
		Vector<Event> eV = eDAO.getAllEventst();
		String eBox = "";

		for (int i = 0; i < 4; i++) {
			eBox = eBox + "<div class=\"col-lg-3 col-md-6 text-center\">" + "<div class=\"service-box\">"
					+ "<div class=\"thumbnail\">"
					+ "<a href=\"/AcroYoga/Veranstaltung?id=" + eV.get(i).getId() + "\"> <img src=\"img/header.jpg\""
					+ "alt=\"Lights\" style=\"width: 100%\"> " 
					+ "<div class=\"caption\">" 
					+ "<h3>" + eV.get(i).getName() +"</h3>"
					+ "<hr class=\"divider\">" 
					+ "<p>" + eV.get(i).getShortContent() +"</p>" 
					+ "</div>" 
					+ "</a>" 
					+ "</div>" 
					+ "</div>"
					+ "</div>";

		}

		request.setAttribute("eBox", eBox);
		
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
