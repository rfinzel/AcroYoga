package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import daos.ForumDAO;
import daos.ThreadDAO;
import objects.Event;
import objects.Forum;
import objects.Thread;


/**
 * Servlet implementation class Forum
 */
@WebServlet("/Forum")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ForumDAO fDAO = new ForumDAO();
		Forum f = fDAO.getForumById(Integer.parseInt(request.getParameter("id")));

		
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

		request.setAttribute("name", f.getName());
		request.setAttribute("headerText", headerText);
		
		List<Thread> tV = fDAO.getThreadsByForum(f.getId());
		String threadListe = "";
		
		for(int i=0; i< tV.size(); i++){
			threadListe = threadListe + "<div class=\"panel-body\">" + tV.get(i).getName() + "</div>";
		}
		request.setAttribute("threadListe", threadListe);

		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/views/Forum.jsp");

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
