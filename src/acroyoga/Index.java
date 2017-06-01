package acroyoga;

import java.io.IOException;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


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
		InitialContext cxt = null;
		DataSource ds = null;
		Connection conn = null;
		Statement stmt = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
			cxt = new InitialContext();
		
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
			
			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from blablabla");
			while (rs.next()) {
	            String coffeeName = rs.getString("ID");
	            System.out.println(coffeeName);
	        }
			conn.close();

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		/*Connection conn = null;
		Statement stmt = null;  // Or PreparedStatement if needed
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
	
			// /jdbc/postgres is the name of the resource above 
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");
			//DriverManager.registerDriver(new org.postgresql.Driver());
		    conn = ds.getConnection();
		    System.out.println("conn success");
		    stmt = conn.createStatement();
		    System.out.println("stmt success");
		    rs = stmt.executeQuery("select * from test");
		    System.out.println("query success");
		    
		    rs.close();
		    rs = null;
		    stmt.close();
		    stmt = null;
		    conn.close(); // Return to connection pool
		    conn = null;  // Make sure we don't close it twice
		} catch (SQLException | NamingException e) {
		    System.out.println(e.toString());
		} finally {
		    // Always make sure result sets and statements are closed,
		    // and the connection is returned to the pool
	    if (rs != null) {
	      try { rs.close(); } catch (SQLException e) { ; }
	      rs = null;
	    }
	    if (stmt != null) {
	      try { stmt.close(); } catch (SQLException e) { ; }
	      stmt = null;
	    }
	    if (conn != null) {
	      try { conn.close(); } catch (SQLException e) { ; }
	      conn = null;
	    }
	  }*/
		
		/*String user = null;
		String headerText = null;
		HttpSession session = request.getSession();
		boolean angemeldet = true;
		
		if(angemeldet)
		{
			user = "Rene";		
			headerText = "Hallo " + user;
		}
		else
		{
			user = "Anmelden";
			headerText = "AcroYoga";
		}
			
		request.setAttribute("user", user);
		request.setAttribute("abc", "<li><a class=\"page-scroll\" href=\"#\">a</a></li><li><a class=\"page-scroll\" href=\"#\">a</a></li>");
		request.setAttribute("headerText", headerText);
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/views/Index.jsp");

        dispatcher.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
