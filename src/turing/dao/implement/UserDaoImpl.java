package turing.dao.implement;

import org.sqlite.core.DB;
import turing.Model.User;
import turing.dao.UserDao;
import turing.util.db.DBUtils;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) throws SQLException {
        Connection conn = null;
        Statement stat = null;
        String sql = String.format("insert into user values('%s', '%s')", user.getUsername(), user.getPassword());
        try {
            conn = DBUtils.getConnection();
            stat = conn.createStatement();
            stat.executeUpdate(sql);
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("添加数据失败");
        } finally {
            DBUtils.close(null, conn, stat);
        }
    }

    @Override
    public User getByName(String name) throws SQLException {
        Connection conn = null;
        Statement stat = null;
        String sql = String.format("select * from user where username = '%s'", name);
        try {
            conn = DBUtils.getConnection();
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            User user = new User();
            while (rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("获取数据失败");
        } finally {
            DBUtils.close(null, conn, stat);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection conn = null;
        Statement stat = null;
        String sql = "select * from user";
        User user = null;
        List<User> users = new ArrayList<User>();
        try {
            conn = DBUtils.getConnection();
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
