package turing.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import turing.Model.Comment;
import turing.dao.CommentDao;
import turing.dao.impl.CommentDaoJdbcImpl;


class CommentDaoJdbcImplTest {
    private CommentDao commentDao = new CommentDaoJdbcImpl();
    @BeforeAll
    void save() {
        Comment comment = new Comment();
        comment.setUsername("test");
        comment.setContent("123");
        comment.setCreate_time("2018-06-06 18:37");
        comment.setWeek(12);
        commentDao.save(comment);
    }

    @Test
    void queryByWeek() {
        assert !commentDao.queryByWeek(12).isEmpty();
    }
}