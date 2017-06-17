package daos;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import objects.Event;
import objects.Member;

public class EventDAO {
	private Connection conn;
	private ConnectionProvider conProvider;

	
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
				e = new Event(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4),rs. getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
			}
		} catch(SQLException e1) {
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
		
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return e;		
	}
	
	public Vector<Event> searchEvents(String search_param)
	{
		Vector<Event> e = new Vector<Event>();
		String search = "%" + search_param + "%";
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from event where name like ? or shortcontent like ? or place like ? or content like ?");
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setString(4, search);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				e.add(getEventById(rs.getInt(1)));
			}
		} catch(SQLException e1) {
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
	
	public int addEvent(Event e)
	{
		conn = conProvider.getConnection();
		int id = getNextId();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into event values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, e.getName());
			pstmt.setTimestamp(3, e.getTiming());			
			pstmt.setInt(4, e.getRegularity());
			pstmt.setString(5, e.getPlace());
			pstmt.setString(6, e.getShortContent());
			pstmt.setString(7, e.getContent());
			pstmt.setDouble(8, e.getFee());
			pstmt.setInt(9, e.getInstructor());
			pstmt.executeUpdate();
		} catch(SQLException e1) {
			id = -1;
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return id;
	}
	
	private int getNextId()
	{
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from event order by id desc");		
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			id = rs.getInt(1);
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return id+1;	
	}
	
	public Vector<Event> getEventsByMember(int member)
	{
		Vector<Event> e = new Vector<Event>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select evnt from participation where participants = ?");
			pstmt.setInt(1, member);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println("addmember");
				e.add(getEventById(rs.getInt(1)));
			}
		} catch(SQLException e1) {
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
}
