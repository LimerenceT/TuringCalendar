package turing.dao;

import turing.Model.Comment;

import java.util.List;

public interface CommentDao {
    public void save(Comment comment);

    public List<Comment> queryByWeek(int week);

}
