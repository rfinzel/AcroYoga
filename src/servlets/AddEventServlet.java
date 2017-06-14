package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.EventDAO;
import objects.Event;
import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/PlusVeranstaltung")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		EventDAO eDAO = new EventDAO();
		MemberDAO mDAO = new MemberDAO();
		String user = "Celina";

		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("user")) {
					user = c.getValue();
					request.setAttribute("user", user);
				}
			}
		}

		
		String name = "";
		String content = "";
		String place = "";
		String timing = "";
		String regularity = "";
		String fee = "";
		
		name = request.getParameter("name");
		content = request.getParameter("content");
		place = request.getParameter("place");
		timing = request.getParameter("date");
		regularity = request.getParameter("regularity");
		fee = request.getParameter("fee");
		
		
		//eDAO.addEvent(new Event(0,name, timing, regularity, place, content, content, fee, mDAO.getMemberByName(user)));
		
		request.setAttribute("loginbtn", "<a href=\"#about\" class=\"btn btn-primary btn-xl page-scroll\">Anmelden</a>");
		
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/views/Event.jsp");

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
