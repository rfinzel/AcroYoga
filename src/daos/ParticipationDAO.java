package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Participation;
import objects.Thread;


public class ParticipationDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public ParticipationDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public Participation getParticipationById(int id)
	{
		Participation p = null;
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from participation where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				p = new Participation(rs.getInt(1), rs.getDate(3), rs.getInt(4), rs.getInt(5));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		return p;		
	}

}
