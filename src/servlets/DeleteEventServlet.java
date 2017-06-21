package servlets;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.EventDAO;
import daos.MemberDAO;
import daos.PostDAO;
import objects.Event;
import objects.Member;
import objects.Post;

/**
 * Servlet implementation class Event
 */
@WebServlet("/DeleteEvent")
@MultipartConfig
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteEventServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DAOs
		EventDAO eDAO = new EventDAO();
		MemberDAO mDAO = new MemberDAO();
		PostDAO pDAO = new PostDAO();

		//TODO Man kann nicht 2 nacheinander löschen, Error: EventDAO:142
		// Attribute aus der JSP
		Event e = eDAO.getEventById(Integer.parseInt(request.getParameter("id")));
				
		//Attribute aus de DB
		Member m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		List<Event> eLin = eDAO.getEventsByMember(m.getId());
		request.setAttribute("eLin", eLin);
		
		// Login Information an JSP weiterleiten um bestimmte Elemente einzublenden
		request.setAttribute("loggedIn", true);
		request.setAttribute("user", m);

		List<Post> pLin = pDAO.getPostsByAuthor(m.getId());
		for (Post p : pLin) {
			p.setContent(p.getContent().substring(0, Math.min(p.getContent().length(), 25)));
		}
		request.setAttribute("pLin", pLin);

		
		// Event erstellen und ID f�r die Ordner zwischenspeichern
		boolean deleted = eDAO.deleteEvent(e);
		request.setAttribute("deleted", deleted);

		//TODO Wie löscht man diese? Habe dafür den gesamten unterbau mal da gelassen :)
		// Wenn das Anlegen geklappt hat, Orderstruktur löchen
		/*if (id >= 0) {
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id).mkdir();
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/images").mkdir();

			Part filePart = request.getPart("file");
			
			File file = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id, "image.jpg");
			
			try (InputStream fileContent = filePart.getInputStream()) {
				Files.copy(fileContent, file.toPath());
			}
			
			// Thumbnail vom Bild erstellen
			createThumbnail(file, request.getSession().getServletContext().getRealPath("img") + "/events/" + id);
			
			// Auf die Seite des erstellen Events weiterleiten
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/Event?id=" + id);

			dispatcher.forward(request, response);
		} else {
			// Auf Event-erstellen weiterleiten
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/views/AddEvent.jsp");

			dispatcher.forward(request, response);
		}*/
		
		String path = request.getHeader("referer");
		//response.sendRedirect(path.substring(21));
		
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
		doGet(request, response);
	}

	private void createThumbnail(File file, String path) throws IOException {
		BufferedImage originalBufferedImage = null;
		try {			
			originalBufferedImage = ImageIO.read(file);
		} catch (IOException ioe) {
			System.out.println("IO exception occurred while trying to read image.");
			throw ioe;
		}

		int thumbnailWidth = 150;

		int widthToScale, heightToScale;
		if (originalBufferedImage.getWidth() > originalBufferedImage.getHeight()) {

			heightToScale = (int) (1.1 * thumbnailWidth);
			widthToScale = (int) ((heightToScale * 1.0) / originalBufferedImage.getHeight()
					* originalBufferedImage.getWidth());

		} else {
			widthToScale = (int) (1.1 * thumbnailWidth);
			heightToScale = (int) ((widthToScale * 1.0) / originalBufferedImage.getWidth()
					* originalBufferedImage.getHeight());
		}

		BufferedImage resizedImage = new BufferedImage(widthToScale, heightToScale, originalBufferedImage.getType());
		Graphics2D g = resizedImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(originalBufferedImage, 0, 0, widthToScale, heightToScale, null);
		g.dispose();

		int x = (resizedImage.getWidth() - thumbnailWidth) / 2;
		int y = (resizedImage.getHeight() - thumbnailWidth) / 2;

		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Width of new thumbnail is bigger than original image");
		}

		BufferedImage thumbnailBufferedImage = resizedImage.getSubimage(x, y, thumbnailWidth, thumbnailWidth);
		
		try {
		    ImageIO.write(thumbnailBufferedImage, "JPG", new File(path + "/thumbnail.jpg"));
		}
		catch (IOException ioe) {
		    System.out.println("Error writing image to file");
		    throw ioe;
		}
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
