package servlets;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import daos.EventDAO;
import objects.Event;
import daos.MemberDAO;
import daos.PostDAO;
import objects.Member;
import objects.Post;

/**
 * Servlet implementation class Event
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOs
		MemberDAO mDAO = new MemberDAO();
		EventDAO eDAO = new EventDAO();
		PostDAO pDAO = new PostDAO();
		
		// Objects
		Member m = mDAO.getMemberByMail(request.getParameter("username"));
		
		//TODO Stürzt noch ab, wenn man sich falsch einlogt
		
		// Wenn der Benutzername existiert
		if (m != null) 
		{
			// Passwort �berpr�fen
			if((request.getParameter("password").equals(m.getPassword())))
			{
				request.getSession().setAttribute("id", m.getId()); // Session Attribut setzen
			}
			
		}
		else
		{
			boolean loggedIn = false;

			request.setAttribute("loggedIn", loggedIn);
			
			List<Event> eLout = eDAO.getAllEvents();
			request.setAttribute("eList", eLout);
			List<Post> pLout = pDAO.getAllPosts();
			request.setAttribute("pLin", pLout);
				
			request.setAttribute("loginError", "Das Passwort oder die Email-Addresse ist inkorrekt.");
		}
		

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
