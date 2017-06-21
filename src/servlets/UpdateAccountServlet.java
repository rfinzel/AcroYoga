package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/UpdateAccount")
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAOs
		MemberDAO mDAO = new MemberDAO();
		
		// Objects
		Member m = null;
		
		// Variablen mit Parameter f�llen
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		
		// Member updaten
		m = mDAO.getMemberById(Integer.parseInt(request.getSession().getAttribute("id").toString()));
		
		Member tempM = new Member(m.getId(), m.getAdmin(), email, password, name, lastname, formatDate(birthday));
		mDAO.updateMember(tempM);		
						
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
	
	private java.sql.Date formatDate(String date)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		java.util.Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(parsed.getTime());
	}
	
	private java.sql.Timestamp formatTimestamp(String timestamp)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		java.util.Date parsed = null;
		
		try {
			parsed = format.parse(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new java.sql.Timestamp(parsed.getTime());
	}


}
