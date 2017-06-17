package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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
import daos.ForumDAO;
import objects.Event;
import objects.Forum;

import objects.Thread;
import daos.MemberDAO;
import daos.ThreadDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/AddThread")
public class AddingThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		boolean loggedIn = false;

		Cookie user = null;
		Member m = null;
		MemberDAO mDAO = new MemberDAO();
		ThreadDAO tDAO = new ThreadDAO();

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

		
		tDAO.addThread(new Thread(0, request.getParameter("threadname"), new Timestamp(System.currentTimeMillis()), 12345, 12345));
		
		
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/Forum?id="+request.getParameter(arg0));

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
