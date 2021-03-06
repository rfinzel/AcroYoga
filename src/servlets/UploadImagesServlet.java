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

import daos.MemberDAO;
import objects.Member;

/**
 * Servlet implementation class UploadImagesServlet
 */
@WebServlet("/UploadImages")
@MultipartConfig
public class UploadImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImagesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Event ID
		String id = request.getParameter("id");
		/**
		 * Hier muss der Ordner noch nach dem Datum von dne Bildern benannt werden
		 */
		// Alle Bilder hochladen //TODO überschreibt jedesmal die alten Bilder
		for (int i = 0; i < Integer.parseInt(request.getParameter("amount")); i++) {
			Part filePart = request.getPart("file" + (i + 1));
			
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/" + request.getParameter("date")).mkdir();
			new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/" + request.getParameter("date") + "/thumbnails").mkdir();
			File file = new File(request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/" + request.getParameter("date"),
					(i + 1) + ".jpg");

			try (InputStream fileContent = filePart.getInputStream()) {
				Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}

			// Thumbnail erstellen
			createThumbnail(file,
					request.getSession().getServletContext().getRealPath("img") + "/events/" + id + "/" + request.getParameter("date") + "/thumbnails",
					Integer.toString(i + 1));
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Event?id=" + id);

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

	private void createThumbnail(File file, String path, String name) throws IOException {
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
			ImageIO.write(thumbnailBufferedImage, "JPG", new File(path + "/" + name + ".jpg"));
		} catch (IOException ioe) {
			System.out.println("Error writing image to file");
			throw ioe;
		}
	}

}
