package turing.test;

import org.junit.jupiter.api.Test;
import turing.db.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;


class JdbcUtilsTest {
    private static Connection connection;
    @Test
    void getConnection() throws SQLException {
        connection = JdbcUtils.getConnection();
        assert connection != null;
    }

    @Test
    void releaseConnection() throws SQLException {
        JdbcUtils.releaseConnection(connection);
    }
}