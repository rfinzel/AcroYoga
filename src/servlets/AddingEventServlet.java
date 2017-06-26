package servlets;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import daos.EventDAO;
import objects.Event;
import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class Event
 */
@WebServlet("/AddingEvent")
@MultipartConfig
public class AddingEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingEventServlet() {
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
		EventDAO eDAO = new EventDAO();

		// Attribute aus der JSP
		String name = request.getParameter("eventname");
		String content = request.getParameter("content");
		String place = request.getParameter("place");
		String timing = request.getParameter("date") + " " + request.getParameter("timing") + ":00";
		int regularity = Integer.parseInt(request.getParameter("regularity"));
		double fee = Double.parseDouble(request.getParameter("fee"));
		String endDate = request.getParameter("endDate");
		
		// Event erstellen und ID für die Ordner zwischenspeichern
		int id = eDAO.addEvent(new Event(0, name, formatTimestamp(timing), regularity, place, content, content, fee, 3, formatDate(endDate)));

		// Wenn das Anlegen geklappt hat, Orderstruktur erstellen
		if (id >= 0) {
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id).mkdir();

			Part filePart = request.getPart("file");
			
			File file = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id, "image.jpg");
			
			try (InputStream fileContent = filePart.getInputStream()) {
				Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
		}
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
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		java.util.Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Date(parsed.getTime());
	}
	
	private java.sql.Timestamp formatTimestamp(String timestamp)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		java.util.Date parsed = null;
		
		try {
			parsed = format.parse(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new java.sql.Timestamp(parsed.getTime());
	}

}
