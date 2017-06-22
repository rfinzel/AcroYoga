package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import objects.Event;
import objects.Member;

public class MemberDAO {
	private Connection conn;
	private ConnectionProvider conProvider;

	public MemberDAO() {
		conProvider = new ConnectionProvider();
	}

	public int addMember(Member m) {
		conn = conProvider.getConnection();
		int id = getNextId();
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into members values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPassword());
			pstmt.setString(5, m.getName());
			pstmt.setString(6, m.getLastname());
			pstmt.setDate(7, m.getBirthday());
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

	public Vector<Member> searchMembers(String search_param) {
		Vector<Member> m = new Vector<Member>();
		String search = "%" + search_param + "%";
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("select id from members where email like ? or name like ? or lastname like ?");
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				m.add(getMemberById(rs.getInt(1)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return m;
	}

	private int getNextId() {
		int id = 0;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from members order by id desc");
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		return id + 1;
	}

	public Member getMemberById(int id) {
		Member m = null;
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from members where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return m;
	}

	public Member getMemberByMail(String mail) {
		Member m = null;

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from members where email = ?");
			pstmt.setString(1, mail);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return m;
	}

	public Member getMemberByName(String name) {
		Member m = null;

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from members where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return m;
	}

	public Vector<Member> getMembersByEvent(int event) {
		Vector<Member> m = new Vector<Member>();

		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select participants from participation where event = ?");
			pstmt.setInt(1, event);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("addmember");
				m.add(getMemberById(rs.getInt(1)));
			}
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return m;
	}

	public void updateMember(Member m) {
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"update members set admin = ?, email= ?, password = ?, name = ?, lastname = ?, birthday = ? where id = ?");
			pstmt.setBoolean(1, m.getAdmin());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPassword());
			pstmt.setString(4, m.getName());
			pstmt.setString(5, m.getLastname());
			pstmt.setDate(6, m.getBirthday());
			pstmt.setInt(7, m.getId());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void deleteMember(Member m) {
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"DELETE FROM members WHERE id = ?");
			pstmt.setInt(1, m.getId());

			pstmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(e1.toString());
		}

		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public Vector<Member> getAllMembers()
	{
		Vector<Member> m = new Vector<Member>();
		
		conn = conProvider.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select id from members");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
				
			{
				m.add(getMemberById(rs.getInt(1)));
			}
		} catch(SQLException e1) {
			System.out.println(e1.toString());
		}
		
		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return m;		
	}

}
