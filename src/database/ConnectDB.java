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

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
