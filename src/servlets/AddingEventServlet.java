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
		boolean loggedIn = false;

		Member m = null;
		MemberDAO mDAO = new MemberDAO();
		EventDAO eDAO = new EventDAO();
		
		if(request.getSession().getAttribute("id") != null){
			m = mDAO.getMemberById((Integer)request.getSession().getAttribute("id"));
		}
		request.setAttribute("user", m);

		if (m != null)
			loggedIn = true;

		request.setAttribute("loggedIn", loggedIn);


		String name = "";
		String content = "";
		String place = "";
		String timing = "";
		int regularity = 0;
		double fee = 0;
		String endDate = "";

		name = request.getParameter("eventname");
		content = request.getParameter("content");
		place = request.getParameter("place");
		timing = request.getParameter("date") + " " + request.getParameter("timing") + ":00";
		regularity = Integer.parseInt(request.getParameter("regularity"));
		fee = Double.parseDouble(request.getParameter("fee"));
		endDate = request.getParameter("endDate");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		java.util.Date parsed = null;
		try {
			parsed = format.parse(timing);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Timestamp sql = new java.sql.Timestamp(parsed.getTime());

		format = new SimpleDateFormat("yyyy.MM.dd");
		parsed = null;
		try {
			parsed = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(parsed.getTime());
		
		int id = eDAO.addEvent(new Event(0, name, sql, regularity, place, content, content, fee, 0, date));

		
		if (id >= 0) {
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id).mkdir();
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/images").mkdir();

			Part filePart = request.getPart("file");
			
			File file = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id, "image.jpg");
			
			try (InputStream fileContent = filePart.getInputStream()) {
				Files.copy(fileContent, file.toPath());
			}
			
			// Thumbnail vom Bild erstellen
			createThumbnail(file, request.getSession().getServletContext().getRealPath("img") + "/events/" + id);
			
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/Event?id=" + id);

			dispatcher.forward(request, response);
		} else {
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

}
