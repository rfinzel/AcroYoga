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
import daos.MemberDAO;
import daos.PostDAO;
import objects.Event;
import objects.Member;
import objects.Post;

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
		// Variablen
		boolean loggedIn = false;

		// DAOs
		MemberDAO mDAO = new MemberDAO();
		EventDAO eDAO = new EventDAO();
		PostDAO pDAO = new PostDAO();
		
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

		// Eventlist und Postlist je nachdem ob der Benutzer eingeloggt ist, oder nicht
		if (loggedIn) { // Eventlist und Postlist loggedIn
			List<Event> eLin = eDAO.getEventsByMember(m.getId());
			request.setAttribute("eLin", eLin);

			List<Post> pLin = pDAO.getPostsByAuthor(m.getId());
			for (Post p : pLin) {
				p.setContent(p.getContent().substring(0, Math.min(p.getContent().length(), 25)));
			}
			request.setAttribute("pLin", pLin);

		} else { // Eventlist not loggedIn
			List<Event> eLout = eDAO.getAllEvents();
			request.setAttribute("eLout", eLout);
			
			List<Post> pLout = pDAO.getAllPosts();
			request.setAttribute("pLout", pLout);
		}

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
