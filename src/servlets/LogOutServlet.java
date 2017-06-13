package servlets;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import daos.EventDAO;
import objects.Event;
import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/Index")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loggedIn = true;
		
		MemberDAO mDAO = new MemberDAO();
		Member m = mDAO.getMemberByMail(request.getParameter("username"));
		
		String user = null;
		String headerText = null;
		
		if((request.getParameter("password").equals(m.getPassword())))
		{
			user = m.getName();	
			Cookie loginCookie = new Cookie("user",user);
			//setting cookie to expiry in 30 mins
			response.addCookie(loginCookie);
			response.sendRedirect("Index.jsp");
		
			headerText = "Hallo " + user;
			loggedIn = true;
			
			request.setAttribute("user", user);
			request.setAttribute("headerText", headerText);
			request.setAttribute("loggedIn", true);
		}
		else
		{
			headerText = "AcroYoga";
			request.setAttribute("user", "Du");
			request.setAttribute("headerText", headerText);
		}
		
		
		
		//Eventliste
				EventDAO eDAO = new EventDAO();
				List<Event> eL = eDAO.getAllEvents();

				request.setAttribute("eList", eL);
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/views/Index.jsp");

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
