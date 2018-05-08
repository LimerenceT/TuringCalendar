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
    public void updated(User user) throws SQLException {
        String sql = "update user set password = ?, liked = ?, disliked = ?";
        update(sql, user.getPassword(), user.getLiked(), user.getDisliked());
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

    @Override
    public boolean state(User user, String week) throws SQLException {
        return user.getLiked().contains(week) || user.getDisliked().contains(week);
    }

    @Override
    public void up(User user) throws SQLException {
        User newUser = getByName(user.getUsername());
        List<String> list = newUser.getLiked();
        list.addAll(user.getLiked());
        newUser.setLiked(list);
        updated(newUser);
    }

    @Override
    public void down(User user) throws SQLException {
        User newUser = getByName(user.getUsername());
        List<String> list = newUser.getDisliked();
        list.addAll(user.getDisliked());
        newUser.setDisliked(list);
        updated(newUser);
    }

}
