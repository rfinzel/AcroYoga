package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
import objects.Post;
import objects.Thread;

/**
 * Servlet implementation class Thread
 */
@WebServlet("/Thread")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThreadServlet() {
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

		// DAOs
		PostDAO pDAO = new PostDAO();
		MemberDAO mDAO = new MemberDAO();
		ThreadDAO tDAO = new ThreadDAO();

		// Objects
		Member m = null;
		Thread t = null;
		
		// Ist eingeloggt?
		if(request.getSession().getAttribute("id") != null){
			m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		}
		request.setAttribute("user", m);

		if (m != null)
			loggedIn = true;

		// Liste aller Post des Threads, ThreadID und Thread Objekt
		List<Post> pList = pDAO.getPostsByThreadAsc(Integer.parseInt(request.getParameter("id")));
		t = tDAO.getThreadById(Integer.parseInt(request.getParameter("id")));

		// Variablen f�r die JSP setzen
		request.setAttribute("loggedIn", loggedIn);
		request.setAttribute("pList", pList);
		request.setAttribute("thread", t);

		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/views/Thread.jsp");

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
