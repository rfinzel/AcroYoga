package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionProvider {
	private InitialContext cxt = null;
	private DataSource ds = null;
	private Connection conn = null;
	
	public ConnectionProvider(){
		
	}
	
	public Connection getConnection() {
		try {
			cxt = new InitialContext();
		
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
			
			conn = ds.getConnection();
			
			/*stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from blablabla");
			while (rs.next()) {
	            String coffeeName = rs.getString("ID");
	            System.out.println(coffeeName);
	        }
			conn.close();*/

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	
		return conn;
	}
}
