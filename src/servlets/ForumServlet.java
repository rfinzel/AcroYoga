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
		
		List<Thread> tList = fDAO.getThreadsByForum(f.getId());
		
		request.setAttribute("tList", tList);

		
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
