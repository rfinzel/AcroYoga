package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import objects.Event;
import objects.Member;

public class MemberDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public MemberDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Member getMemberById(int id)
	{
		Member m = null;
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from member where id = '" + id + "'");
			
			while(rs.next())
			{
				System.out.println("getmember");
				m = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return m;		
	}
	
	public Member getMemberByMail(String mail)
	{
		Member m = null;
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from members where email = '" + mail + "'");
			
			while(rs.next())
			{
				m = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return m;		
	}
	
	public Vector<Member> getMembersByEvent(int event)
	{
		Vector<Member> m = new Vector<Member>();
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select participants from participation where event = '" + event +  "'");
			
			while(rs.next())
			{
				System.out.println("addmember");
				m.add(getMemberById(rs.getInt(1)));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return m;		
	}

}
