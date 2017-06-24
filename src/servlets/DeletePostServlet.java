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
		PostDAO pDAO = new PostDAO();

		// Attribute aus der JSP
		Post p = pDAO.getPostById(Integer.parseInt(request.getParameter("id")));

		//Attribute aus de DB

		// Post löschen
		boolean deleted = pDAO.deletePost(p);

		
		String path = request.getHeader("referer");
		
		RequestDispatcher dispatcher;
		if(path.substring(30) != "/Index")
		{
			dispatcher 
			= this.getServletContext().getRequestDispatcher("/Thread?id=" + p.getThread_id());
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
