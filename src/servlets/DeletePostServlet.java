package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.EventDAO;
import daos.ForumDAO;
import objects.Event;
import objects.Forum;

import objects.Thread;
import daos.MemberDAO;
import daos.PostDAO;
import daos.ThreadDAO;
import objects.Member;
import objects.Post;

/**
 * Servlet implementation class Event
 */
@WebServlet("/DeletePost")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOs
		MemberDAO mDAO = new MemberDAO();
		PostDAO pDAO = new PostDAO();
		EventDAO eDAO = new EventDAO();

		// Attribute aus der JSP
		Post p = pDAO.getPostById(Integer.parseInt(request.getParameter("id")));
		
		//Attribute aus de DB
		Member m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		List<Event> eLin = eDAO.getEventsByMember(m.getId());
		request.setAttribute("eLin", eLin);
		
		// Login Information an JSP weiterleiten um bestimmte Elemente einzublenden
		request.setAttribute("loggedIn", true);
		request.setAttribute("user", m);

		List<Post> pLin = pDAO.getPostsByAuthor(m.getId());
		for (Post po : pLin) {
			po.setContent(p.getContent().substring(0, Math.min(p.getContent().length(), 25)));
		}
		request.setAttribute("pLin", pLin);

		// Post löschen
		boolean deleted = pDAO.deletePost(p);
		request.setAttribute("deletedPost", deleted);

		
		String path = request.getHeader("referer");
		//response.sendRedirect(path.substring(21));
		
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
		= this.getServletContext().getRequestDispatcher(path.substring(30));

        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
