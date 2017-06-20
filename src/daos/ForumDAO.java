package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Forum;
import objects.Thread;

public class ForumDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;

	public ForumDAO() {
		conProvider = new ConnectionProvider();
	}

	public Forum getForumById(int id) {
		Forum f = null;

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from forum where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				f = new Forum(rs.getInt(1), rs.getString(2));
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

		return f;
	} 

	public Vector<Forum> getAllForen() {
		Vector<Forum> e = new Vector<Forum>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from forum");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				e.add(getForumById(rs.getInt(1)));
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

		return e;
	}

	public Vector<Thread> getThreadsByForum(int id) {
		Vector<Thread> t = new Vector<Thread>();

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from thread where forum_id = ? order by timing desc");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				t.add(new Thread(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5)));
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

		return t;
	}

	public void addForum(Forum f) {
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into forum values(?, ?)");
			pstmt.setInt(1, getNextId());
			pstmt.setString(2, f.getName());
			pstmt.executeUpdate();
		} catch (SQLException f1) {
			System.out.println(f1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private int getNextId() {
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from forum order by id desc");
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			id = rs.getInt(1);
		} catch (SQLException f1) {
			System.out.println(f1.toString());
		}

		return id + 1;
	}

}
