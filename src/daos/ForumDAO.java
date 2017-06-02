package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Event;
import objects.Forum;
import objects.Member;

public class ForumDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public ForumDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Forum getMemberById(int id)
	{
		Forum f = null;
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from member where id = '" + id + "'");
			
			while(rs.next())
			{
				f = new Forum(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return f;		
	}

}
