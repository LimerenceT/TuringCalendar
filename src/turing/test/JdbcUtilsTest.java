package turing.test;

import org.junit.jupiter.api.Test;
import turing.db.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUtilsTest {

    @org.junit.jupiter.api.Test
    void getConnection() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
}