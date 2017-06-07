package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
				t = new Thread(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return t;		
	}
	
	

}
