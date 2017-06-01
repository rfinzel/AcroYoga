package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.VeranstaltungObj;

public class VeranstaltungDAO {
	private Connection conn;
	private ConnectionProvider conProvider;
	private Statement stmt;
	private ResultSet rs;
	
	public VeranstaltungDAO()
	{
		conProvider = new ConnectionProvider();
	}
	
	public VeranstaltungObj getVeranstaltungById(int id)
	{
		VeranstaltungObj v = null;
		
		conn = conProvider.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from veranstaltung where id = '" + id + "'");
			
			while(rs.next())
			{
				v = new VeranstaltungObj(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		return v;		
	}

}
