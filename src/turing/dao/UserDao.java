package turing.dao;

import turing.Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //添加user的方法
    public void add(User user) throws SQLException;

    //查找user的方法
    public User getByName(String name) throws SQLException;

    //查找所有
    public List<User> getAll() throws SQLException;
}
