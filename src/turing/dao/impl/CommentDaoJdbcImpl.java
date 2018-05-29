package turing.dao.impl;

import turing.Model.Comment;
import turing.dao.CommentDao;
import turing.dao.DAO;

import java.util.List;

public class CommentDaoJdbcImpl extends DAO<Comment> implements CommentDao{


    @Override
    public void save(Comment comment) {
        String sql = "insert into comment(username, content, week, create_time) values(?,?,?,?)";
        update(sql,comment.getUsername(), comment.getContent(), comment.getWeek(), comment.getCreate_time());
    }

    @Override
    public List<Comment> queryByWeek(int week) {
        String sql = "select * from comment where week = ?";
        return getAll(sql, week);
    }
}
