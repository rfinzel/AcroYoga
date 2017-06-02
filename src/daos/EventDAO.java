package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Event;

public class EventDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public EventDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Event getEventById(int id)
	{
		Event e = null;
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from event where id = '" + id + "'");
			
			while(rs.next())
			{
				e = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return e;		
	}

}
