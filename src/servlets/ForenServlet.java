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
import javax.servlet.http.HttpSession;

import daos.EventDAO;
import daos.ForumDAO;
import daos.MemberDAO;
import objects.Event;
import objects.Forum;
import objects.Member;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Forums")
public class ForenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForenServlet() {
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
		MemberDAO mDAO = new MemberDAO();
		ForumDAO fDAO = new ForumDAO();

		// Objects
		Member m = null;

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

		// Liste aller Foren
		List<Forum> fV = fDAO.getAllForen();
		
		// Foreliste an JSP weiterleiten
		request.setAttribute("fList", fV);

		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/views/Foren.jsp");

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
