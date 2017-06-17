package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.EventDAO;
import daos.ForumDAO;
import objects.Event;
import objects.Forum;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Forums")
public class ForenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ForumDAO fDAO = new ForumDAO();
		List<Forum> fV = fDAO.getAllForen();
		
		request.setAttribute("fList", fV);

		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/views/Foren.jsp");

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
