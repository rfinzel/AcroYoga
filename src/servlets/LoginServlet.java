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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDAO = new MemberDAO();
		Member m = mDAO.getMemberByMail(request.getParameter("username"));
		if (m != null) {
			if((request.getParameter("password").equals(m.getPassword())))
			{
				Cookie loginCookie = new Cookie("user",Integer.toString(m.getId()));
				
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(50*365*24*60*60); //50 Jahre
				response.addCookie(loginCookie);					
			}				
			
		}else{
			boolean loggedIn = false;

			request.setAttribute("loggedIn", loggedIn);

			EventDAO eDAO = new EventDAO();
			PostDAO pDAO = new PostDAO();

			if (loggedIn) { // Eventlist und Postlist loggedIn
				List<Event> eLin = eDAO.getEventsByMember(m.getId());
				request.setAttribute("eLin", eLin);
				
				List<Post> pLin = pDAO.getPostsByAuthor(m.getId());
				request.setAttribute("pLin", pLin);
			} else { // Eventlist not loggedIn
				List<Event> eLout = eDAO.getAllEvents();
				request.setAttribute("eList", eLout);
			}
			
			request.setAttribute("loginError", "Das Passwort oder die Email-Addresse ist inkorrekt.");
			
		}
		

		String path = request.getHeader("referer");
		//response.sendRedirect(path.substring(21));
		
		// TODO Auto-generated method stub
				// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher(path.substring(30));

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
