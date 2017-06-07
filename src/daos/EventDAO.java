package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Event;
import objects.Member;

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
			PreparedStatement pstmt = conn.prepareStatement("select * from event where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				e = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4),rs. getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return e;		
	}

	public Vector<Event> getAllEvents()
	{
		Vector<Event> e = new Vector<Event>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from event order by timing desc");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				e.add(getEventById(rs.getInt(1)));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return e;		
	}
	
}
