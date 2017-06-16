package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Forum;
import objects.Post;
import objects.Thread;


public class ThreadDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public ThreadDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Thread getThreadById(int id)
	{
		Thread t = null;
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from thread where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				t = new Thread(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return t;		
	}
	public void addThread(Thread t)
	{
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into forum values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, getNextId());
			pstmt.setString(2, t.getName());
			pstmt.setTimestamp(3, t.getTiming());
			pstmt.setString(4, t.getName());
			pstmt.setInt(5, t.getId());
			pstmt.executeUpdate();
		} catch(SQLException f1) {
			System.out.println(f1.toString());
		}
	}
	private int getNextId()
	{
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from forum order by id desc");		
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			id = rs.getInt(1);
		} catch(SQLException f1) {
			System.out.println(f1.toString());
		}
		
		return id+1;	
	}
	
	public Vector<Thread> getThreadsByForum(int id)
	{
		Vector<Thread> t = new Vector<Thread>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from thread where forum_id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				t.add(new Thread(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return t;		
	}
	

}
