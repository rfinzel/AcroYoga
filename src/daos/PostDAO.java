package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Event;
import objects.Forum;
import objects.Post;
import objects.Thread;

public class PostDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;

	public PostDAO() {
		conProvider = new ConnectionProvider();
	}

	public Post getPostById(int id) {
		Post p = null;

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p = new Post(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return p;
	}

	public Vector<Post> getPostsByThread(int id) {
		Vector<Post> p = new Vector<Post>();

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post where thread_id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p.add(new Post(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return p;
	}

	public Vector<Post> getPostsByAuthor(int member) {
		Vector<Post> p = new Vector<Post>();

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from post where author = ?");
			pstmt.setInt(1, member);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p.add(getPostById(rs.getInt(1)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return p;
	}
	
	public void addPost(Post p) {
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into post values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, getNextId());
			pstmt.setString(2, p.getContent());
			pstmt.setTimestamp(3, p.getTiming());
			pstmt.setInt(4, p.getAuthor());
			pstmt.setInt(5, p.getThread_id());
			pstmt.executeUpdate();
		} catch (SQLException p1) {
			System.out.println(p1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Vector<Post> getAllPosts() {
		Vector<Post> p = new Vector<Post>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from post");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p.add(getPostById(rs.getInt(1)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return p;
	}
	
	private int getNextId() {
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post order by id desc");
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			id = rs.getInt(1);
		} catch (SQLException f1) {
			System.out.println(f1.toString());
		}
		return id + 1;
	}
}