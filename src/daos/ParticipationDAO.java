package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Member;
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
		MemberDAO mDAO = new MemberDAO();
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from participation where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				p = new Participation(rs.getInt(1), rs.getDate(3), rs.getInt(4), mDAO.getMemberById(rs.getInt(5)));
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
		return p;		
	}
	
	public int addParticipation(Participation p) {
		conn = conProvider.getConnection();
		int id = getNextId();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into participation values(?, ?, ?, ?)");
			pstmt.setInt(1, id);
			pstmt.setDate(2, p.getTiming());
			pstmt.setInt(3, p.getEvent());
			pstmt.setInt(4, p.getParticipant().getId());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return id;
	}
	
	public int deleteParticipation(Participation p) {
		conn = conProvider.getConnection();
		int id = getNextId();
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from participation where timing = ? and event = ? and participants = ?");
			pstmt.setDate(1, p.getTiming());
			pstmt.setInt(2, p.getEvent());
			pstmt.setInt(3, p.getParticipant().getId());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return id;
	}
	
	private int getNextId() {
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from participation order by id desc");
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		return id + 1;
	}


}
