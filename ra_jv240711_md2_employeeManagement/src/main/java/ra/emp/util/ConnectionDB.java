package ra.emp.util;

import java.sql.*;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/springmvc_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
