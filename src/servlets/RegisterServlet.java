package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import daos.EventDAO;
import objects.Event;
import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/Register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DAOs
		MemberDAO mDAO = new MemberDAO();
		
		// Objects
		//Member m = mDAO.getMemberByMail(request.getParameter("email"));
		
		/*
		 * Abfrage ob mail schon vergeben ist
		 */

		// Variablen mit Parametern füllen
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int id = mDAO.addMember(new Member(0, false, email, password, name, lastname, null));

		// Order mit default Profilbild erstellen
		if(id >= 0)
		{
			new File(request.getSession().getServletContext().getRealPath("img") + "/members/" + id).mkdir();
		
			File source = new File(request.getSession().getServletContext().getRealPath("img") + "/default.png");
			File dest = new File(request.getSession().getServletContext().getRealPath("img") + "/members/" + id, "picture.png");
		
			copyFile(source, dest);
		}
		/*
		 * Fehler. ID muss vom neu erstellten Member gesetzt werden
		 */
		request.getSession().setAttribute("id", id);

		String path = request.getHeader("referer");
		
		// TODO Auto-generated method stub
		// Forward to /WEB-INF/views/login.jsp
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher(path.substring(30));

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
	
	private static void copyFile(File sourceFile, File destFile)
	        throws IOException {
	    if (!sourceFile.exists())
	        return;
	    
	    if (!destFile.exists()) 
	        destFile.createNewFile();
	    
	    FileChannel source = null;
	    FileChannel destination = null;
	    
	    source = new FileInputStream(sourceFile).getChannel();
	    destination = new FileOutputStream(destFile).getChannel();
	    
	    if (destination != null && source != null) 
	        destination.transferFrom(source, 0, source.size());
	    
	    if (source != null) 
	        source.close();
	    
	    if (destination != null) 
	        destination.close();
	    

	}

}
