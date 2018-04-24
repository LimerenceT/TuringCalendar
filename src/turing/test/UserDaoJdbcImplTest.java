package turing.test;

import org.junit.jupiter.api.Test;
import turing.Model.User;
import turing.dao.UserDao;
import turing.dao.impl.UserDaoJdbcImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoJdbcImplTest {

    private UserDao userDao = new UserDaoJdbcImpl();

    @Test
    void add() {
        User user = new User();
        user.setUsername("ql");
        user.setPassword("123");
        try {
            userDao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByName() throws SQLException {
        User user = userDao.getByName("sb");

        System.out.println(user);
    }

    @Test
    void getAll() {
        try {
            List<User> users= userDao.getAll();
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}