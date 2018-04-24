package turing.dao.impl;
import turing.Model.User;
import turing.dao.DAO;
import turing.dao.UserDao;


import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbcImpl extends DAO<User> implements UserDao {


    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into user(username, password) values(?, ?)";
        update(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public User getByName(String name) throws SQLException {
        String sql = "select * from user where username = ?";
        return get(sql, name);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "select * from user";
        return getAll(sql);
    }
}
