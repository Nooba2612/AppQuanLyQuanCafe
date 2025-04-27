package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBP {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();

	public static Connection getConnection() {
//		System.out.println("success");
		return con;
	}
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public Connection connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCaPhe";
		con = DriverManager.getConnection(url, "sa", "sapassword");
		return con;
	}

	public void disconnect() throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	

	

	public static void main(String[] args) throws SQLException {
		ConnectDB connect = new ConnectDB();
		ConnectDB.getInstance();
		Connection c = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCaPhe";
		c = DriverManager.getConnection(url, "sa", "sa123");
		System.out.println(c);
		
		if(c== null)
			System.out.println("failed");
		else
			System.out.println("success");
	}

}
