package turing.dao.impl;

import turing.Model.User;
import turing.dao.DAO;
import turing.dao.UserDao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl extends DAO<User> implements UserDao {


    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into user(username, password) values(?, ?)";
        update(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public void updated(User user) throws SQLException {
        String sql = "update user set password = ?, liked = ?, disliked = ? where username = ?";
        update(sql, user.getPassword(), user.getLiked(), user.getDisliked(), user.getUsername());
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
    public List<Boolean> state(User user, String week) throws SQLException {
        List<Boolean> states = new ArrayList<>();
        states.add(user.getLiked().contains(week));
        states.add( user.getDisliked().contains(week));
        return states;
    }

    @Override
    public void up(User user) throws SQLException {
        User newUser = getByName(user.getUsername());
        String liked = newUser.getLiked();
        liked += user.getLiked()+",";
        newUser.setLiked(liked);
        updated(newUser);
    }

    @Override
    public void down(User user) throws SQLException {
        User newUser = getByName(user.getUsername());
        String disliked = newUser.getDisliked();
        disliked += user.getDisliked()+",";
        newUser.setDisliked(disliked);
        updated(newUser);
    }

}
