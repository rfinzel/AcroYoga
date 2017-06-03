package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import daos.EventDAO;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String headerText = null;
		HttpSession session = request.getSession();
		boolean angemeldet = false;
		
		if(angemeldet)
		{
			user = "René";		
			headerText = "Hallo " + user;
		}
		else
		{
			user = "<li class=\"dropdown\">"
				   + "<a class=\"dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\">Sign In <strong class=\"caret\"></strong></a>"
				   + "<div class=\"dropdown-menu\" style=\"padding: 15px; padding-bottom: 0px;\">"
				   + "<form method=\"post\" action=\"Index\" accept-charset=\"UTF-8\">" 
				   +			"<input style=\"margin-bottom: 15px;\" type=\"text\" placeholder=\"Username\" id=\"username\" name=\"username\">"
				   +			"<input style=\"margin-bottom: 15px;\" type=\"password\" placeholder=\"Password\" id=\"password\" name=\"password\">"
				   +				"<input style=\"float: left; margin-right: 10px;\" type=\"checkbox\" name=\"remember-me\" id=\"remember-me\" value=\"1\">"
				   +				"<label class=\"string optional\" for=\"user_remember_me\"> Remember me</label>"
				   + 				"<input class=\"btn btn-primary btn-block\" type=\"submit\" id=\"sign-in\" value=\"Sign In\">"
				   +		"</form>"
				   +	"</div>"
				   +"</li>";
			headerText = "AcroYoga";
		}
			
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cookie loginCookie = null;
		loginCookie = new Cookie("user", username);
		
		//setting cookie to expiry in 30 mins
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
		//response.sendRedirect("LoginSuccess.jsp");
		/*if(username.equals(username) && password.equals(password))
		{
			loginCookie = new Cookie("user", username);
			
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			response.sendRedirect("LoginSuccess.jsp");
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}*/
		
		request.setAttribute("user", username);
		
		request.setAttribute("headerText", headerText);
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/views/Index.jsp");

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
