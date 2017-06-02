package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				m = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return m;		
	}

}
