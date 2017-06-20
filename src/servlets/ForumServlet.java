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
import daos.MemberDAO;
import daos.PostDAO;
import daos.ThreadDAO;
import objects.Event;
import objects.Forum;
import objects.Member;
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
		// Variablen
		boolean loggedIn = false;
		int postCounter;

		// DAOs
		ForumDAO fDAO = new ForumDAO();
		MemberDAO mDAO = new MemberDAO();
		PostDAO pDAO = new PostDAO();
		
		// Objects
		Member m = null;
		Forum f = null;
		
		// Session auslesen, ob ein User angemeldet ist
		if(request.getSession().getAttribute("id") != null){
			m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		}

		// Wenn Member != null, ist der User angemeldet
		if (m != null)
			loggedIn = true;

		// Login Information an JSP weiterleiten um bestimmte Elemente einzublenden
		request.setAttribute("loggedIn", loggedIn);
		request.setAttribute("user", m);

		// Forum auslesen
		f = fDAO.getForumById(Integer.parseInt(request.getParameter("id")));
		
		// Alle Threads im Forum
		List<Thread> tList = fDAO.getThreadsByForum(f.getId());
		request.setAttribute("tList", tList);

		// Postcounter der einzelnen Threads ermitteln
		postCounter = pDAO.countPosts(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("postCounter", postCounter);
		
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
