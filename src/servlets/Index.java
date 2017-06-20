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
		boolean loggedIn = false;

		Cookie user = null;
		Member m = null;
		MemberDAO mDAO = new MemberDAO();

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

		EventDAO eDAO = new EventDAO();
		PostDAO pDAO = new PostDAO();

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
			request.setAttribute("eList", eLout);
			
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
