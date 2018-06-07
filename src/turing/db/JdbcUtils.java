package turing.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    private static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource("TuringCalendar");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

    public static void releaseConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
