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
		boolean loggedIn = false;

		Cookie user = null;
		MemberDAO mDAO = new MemberDAO();
		ForumDAO fDAO = new ForumDAO();
		PostDAO pDAO = new PostDAO();
		int postCounter;
		Member m = null;

		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("user")) {
					user = c;
					m = mDAO.getMemberById(Integer.parseInt(c.getValue()));
				}
			}
		}

		request.setAttribute("user", m);

		if (user != null)
			loggedIn = true;

		request.setAttribute("loggedIn", loggedIn);

		Forum f = fDAO.getForumById(Integer.parseInt(request.getParameter("id")));
		
		List<Thread> tList = fDAO.getThreadsByForum(f.getId());
		
		request.setAttribute("tList", tList);

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
