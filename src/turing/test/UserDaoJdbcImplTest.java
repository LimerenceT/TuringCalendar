package turing.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turing.Model.User;
import turing.dao.UserDao;
import turing.dao.impl.UserDaoJdbcImpl;

import java.sql.SQLException;
import java.util.List;


class UserDaoJdbcImplTest {
    private static UserDao userDao;
    private static User user;
    @BeforeAll
    static void setUp() {
        userDao = new UserDaoJdbcImpl();

        user = new User();
        user.setUsername("test");
        user.setPassword("123");
    }


    @Test
    void add() throws SQLException {
        userDao.add(user);
    }

    @Test
    void getByName() throws SQLException {
        User user = userDao.getByName("test");
        assert user.getUsername().equals("test");
    }

    @Test
    void getAll() throws SQLException {
        List<User> users= userDao.getAll();
        assert !users.isEmpty();

    }
}