package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection con = null;
    private static ConnectDB instance = new ConnectDB();
    private String databaseUser = "Nooba";
    private String databasePassword = "402162dpnguyen";
    private String databaseName = "QuanLyQuanCaPhe";

    public static Connection getConnection() {

        return con;
    }

    public static ConnectDB getInstance() {
        return instance;
    }
    //ket noi

    public Connection connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;
        con = DriverManager.getConnection(url, databaseUser, databasePassword);
        return con;
    }
//	public void connect() throws SQLException{
//		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyQuanCaPhe";
//		String user = "sa";
//		String pwd = "sa123";
//		con = DriverManager.getConnection(url, user, pwd);
//	}
    //đóng kết nối

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

// test có kết nối thành công hay không
//	public static void main(String[] args) {
//		ConnectDB connectDB = ConnectDB.getInstance();
//	    try {
//	        connectDB.connect();
//	        Connection con = connectDB.getConnection();
//	        if (con != null) {
//	            System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
//	            DatabaseMetaData metaData = con.getMetaData();
//	            System.out.println("Sản phẩm cơ sở dữ liệu: " + metaData.getDatabaseProductName());
//	            System.out.println("Phiên bản cơ sở dữ liệu: " + metaData.getDatabaseProductVersion());
//	            System.out.println("Trạng thái kết nối: " + (!con.isClosed() ? "Đang mở" : "Đã đóng"));
//	        } else {
//	            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        connectDB.disconnect();
//	    }
//}
}
