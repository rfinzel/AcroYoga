package servlets;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import daos.EventDAO;
import objects.Directory;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loggedIn = false;
		
		// DAOs
		MemberDAO mDAO = new MemberDAO();
		EventDAO eDAO = new EventDAO();
		
		// Objects
		Member m = null;
		Event e = null;
		
		// Session auslesen, ob ein User angemeldet ist
		if(request.getSession().getAttribute("id") != null){
			m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		}

		// Wenn Member != null, ist der User angemeldet
		if (m != null)
			loggedIn = true;

		// Login Information an JSP weiterleiten um bestimmte Elemente einzublenden
		request.setAttribute("user", m);
		request.setAttribute("loggedIn", loggedIn);
		
		e = eDAO.getEventById(Integer.parseInt(request.getParameter("id")));
		
		// Variablen an JSP weiterleiten
		request.setAttribute("name", e.getName());
		request.setAttribute("loginbtn", "");
		request.setAttribute("place", e.getPlace());
		request.setAttribute("content", e.getContent());
		request.setAttribute("regularity", e.getRegularity());
		request.setAttribute("fee", e.getFee());
		request.setAttribute("id", e.getId());
		
		// Wochentag ermitteln
		String weekday = new SimpleDateFormat("EE").format(e.getTiming());
		request.setAttribute("weekday", weekday);
		
		// Datum und Uhrzeit formatieren
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
		request.setAttribute("time", new SimpleDateFormat("hh-mm").format(e.getTiming()));
				
		request.setAttribute("timing", formatter.format(e.getTiming()));
		request.setAttribute("endDate", formatter.format(e.getEndDate()));
		
		// N�chsten Termin berechnen
		Timestamp nextEvent = e.getTiming();
		
		Calendar cal = Calendar.getInstance();
		
		while(nextEvent.before(new Timestamp(System.currentTimeMillis())))
		{
			cal.setTime(nextEvent);
			cal.add(Calendar.DAY_OF_WEEK, e.getRegularity());
			nextEvent.setTime(cal.getTime().getTime());
		}

		request.setAttribute("nextEvent", formatter.format(e.getNextEvent()));
		
		// Teilnehmer (�berarbeiten)
		List<Member> members = mDAO.getMembersByEvent(Integer.parseInt(request.getParameter("id")));

		boolean participate = false;
		for(int i = 0; i < members.size(); i++)
		{
			if(members.get(i).getId() == Integer.parseInt(request.getSession().getAttribute("id").toString()))
			{
				participate  = true;
				break;
			}
		}
		request.setAttribute("participate", participate);
		request.setAttribute("participants", members);
		
		// Bilder upload zu den Events
		File f = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + e.getId());
		
		// Filtern nach .jpg und .png
		FilenameFilter directoryFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (!lowercaseName.endsWith(".jpg") && !lowercaseName.endsWith(".png")) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		// Filtern nach .jpg und .png
		FilenameFilter pictureFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (!lowercaseName.endsWith(".jpg") && !lowercaseName.endsWith(".png")) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		List<Directory> directories = new Vector<Directory>();
		
		if(f.list(directoryFilter) != null)
		{
			for(int i = 0; i < f.list(directoryFilter).length; i++)
			{
				directories.add(new Directory(f.list(directoryFilter)[i]));
				System.out.println("Ordner:" + f.list(directoryFilter)[i]);
			}
		}
		
		for(int i = 0; i < directories.size(); i++)
		{
			// Bilder upload zu den Events
			File files = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + e.getId() + "/" + directories.get(i).getName());
			
			
			
			directories.get(i).setList(files.list(pictureFilter));
			
			for(int j = 0; j < files.list(pictureFilter).length; j++)
			{
				System.out.println("Files:" + files.list(pictureFilter)[j]);
			}
		}

		request.setAttribute("fileList", directories);
		
		// Forward to /WEB-INF/views/login.jsp
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/views/Event.jsp");

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
