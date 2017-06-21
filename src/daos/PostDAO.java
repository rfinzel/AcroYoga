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
		MemberDAO mDAO = new MemberDAO();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p = new Post(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), mDAO.getMemberById(rs.getInt(4)), rs.getInt(5));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
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
			e1.printStackTrace();
		}
		
		return p;
	}
	
	public Vector<Post> getPostsByThread(int id) {
		Vector<Post> p = new Vector<Post>();
		MemberDAO mDAO = new MemberDAO();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post where thread_id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p.add(new Post(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), mDAO.getMemberById(rs.getInt(4)), rs.getInt(5)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	public Vector<Post> getPostsByThreadAsc(int id) {
		Vector<Post> p = new Vector<Post>();
		MemberDAO mDAO = new MemberDAO();
		
		conn = conProvider.getConnection();
		try {

			PreparedStatement pstmt = conn.prepareStatement("select * from post where thread_id = ? order by timing asc");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				p.add(new Post(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), mDAO.getMemberById(rs.getInt(4)), rs.getInt(5)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
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
			pstmt.setInt(4, p.getAuthor().getId());
			pstmt.setInt(5, p.getThread_id());
			pstmt.executeUpdate();
		} catch (SQLException p1) {
			System.out.println(p1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean deletePost(Post p)
	{ 
		conn = conProvider.getConnection();
		boolean deleted = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from post where id = ?");
			pstmt.setInt(1, p.getId());
			pstmt.executeUpdate();
			deleted = true;
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return deleted;
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
			e1.printStackTrace();
		}

		return p;
	}
	
	public int countPosts(int threadId) {
		int counter = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select count(*) from post where thread_id =?");
			pstmt.setInt(1, threadId);
			ResultSet rs = pstmt.executeQuery();
			

			rs.next();
			counter = rs.getInt(1);
		} catch (SQLException f1) {
			System.out.println(f1.toString());
		}
		return counter;
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