package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Event;
import objects.Post;

public class PostDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public PostDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Post getPostById(int id)
	{
		Post p = null;
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from post where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				p = new Post(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return p;		
	}

	public Vector<Post> getPostsByThread(int id)
	{
		Vector<Post> p = new Vector<Post>();
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from post where thread_id = '" + id + "'");
			
			while(rs.next())
			{
				p.add(new Post(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return p;		
	}
}
