package turing.dao.factory;

import turing.dao.UserDao;
import turing.dao.impl.UserDaoJdbcImpl;

import java.util.HashMap;
import java.util.Map;

public class UserDAOFactory {

    private Map<String, UserDao> daos = new HashMap<>();
    private static UserDAOFactory instance = new UserDAOFactory();
    private String type = null;

    public static UserDAOFactory getInstance() {
        return instance;
    }

    public void  setType(String type) {
        this.type = type;
    }

    private UserDAOFactory() {
        this.daos.put("jdbc", new UserDaoJdbcImpl());
        // add new imp in there
    }

    public UserDao getUserDao() {
        return (UserDao) this.daos.get(this.type);

    }
}
