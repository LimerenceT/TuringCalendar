package turing.dao.factory;

import turing.dao.CommentDao;
import turing.dao.impl.CommentDaoJdbcImpl;

import java.util.HashMap;
import java.util.Map;

public class CommentDaoFactory {

    private Map<String, CommentDao> daos = new HashMap<>();
    private static CommentDaoFactory instance = new CommentDaoFactory();
    private String type = null;

    public static CommentDaoFactory getInstance() {
        return instance;
    }

    public void  setType(String type) {
        this.type = type;
    }

    private CommentDaoFactory() {
        this.daos.put("jdbc", new CommentDaoJdbcImpl());
        // add new imp in there
    }

    public CommentDao getCommentDao() {
        return (CommentDao) this.daos.get(this.type);

    }
}
