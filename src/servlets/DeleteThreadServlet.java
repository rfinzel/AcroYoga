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
@WebServlet("/DeleteThread")
public class DeleteThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteThreadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOs
		ThreadDAO tDAO = new ThreadDAO();

		// Attribute aus der JSP
		Thread t = tDAO.getThreadById(Integer.parseInt(request.getParameter("id")));

		//Attribute aus de DB

		// Post löschen
		boolean deleted = tDAO.deleteThread(t);

		String path = request.getHeader("referer");
		
		RequestDispatcher dispatcher;
		if(path.substring(30) != "/Index")
		{
			dispatcher 
			= this.getServletContext().getRequestDispatcher("/Forum?id=" + t.getForum_id());
		}
		else
		{
			request.setAttribute("deletedPost", deleted);
			dispatcher 
			= this.getServletContext().getRequestDispatcher("/Index");
		}

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
