package turing.util.db;

import java.sql.*;


public class DBUtils {
    // 定义连接sqlite
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite::resource:db/test.db");
            conn.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        System.out.println("获取连接成功");
        return conn;
    }

    //关闭数据库连接
    public static void close(ResultSet rs, Connection conn, Statement stat){
        try {
            if (rs!=null) rs.close();
            if(stat!=null) stat.close();
            if (conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
