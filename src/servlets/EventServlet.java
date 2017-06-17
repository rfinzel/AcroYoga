package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/Event")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		EventDAO eDAO = new EventDAO();
		Event e = eDAO.getEventById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("name", e.getName());
		request.setAttribute("loginbtn", "<a href=\"#about\" class=\"btn btn-primary btn-xl page-scroll\">Anmelden</a>");
		request.setAttribute("place", e.getPlace());
		request.setAttribute("content", e.getContent());
		request.setAttribute("regularity", e.getRegularity());
		request.setAttribute("fee", e.getFee());

		String weekday = new SimpleDateFormat("EE").format(e.getTiming());
		request.setAttribute("weekday", weekday);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
		request.setAttribute("time", new SimpleDateFormat("hh-mm").format(e.getTiming()));
		request.setAttribute("timing", formatter.format(e.getTiming()));

		MemberDAO mDAO = new MemberDAO();
		Vector<Member> members = mDAO.getMembersByEvent(Integer.parseInt(request.getParameter("id")));
		String htmlMembers = "";

		for(int i = 0; i < members.size(); i++)
			htmlMembers = htmlMembers + members.get(i).getName() + " ";
		request.setAttribute("participants", htmlMembers);
		
		
		
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
