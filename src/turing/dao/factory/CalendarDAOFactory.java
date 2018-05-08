package turing.dao.factory;

import turing.dao.CalendarDao;
import turing.dao.impl.CalendarDaoJdbcImpl;

import java.util.HashMap;
import java.util.Map;

public class CalendarDAOFactory {
    private Map<String, CalendarDao> daos = new HashMap<>();
    private static CalendarDAOFactory instance = new CalendarDAOFactory();
    private String type = null;

    public static CalendarDAOFactory getInstance() {
        return instance;
    }

    public void  setType(String type) {
        this.type = type;
    }

    private CalendarDAOFactory() {
        this.daos.put("jdbc", new CalendarDaoJdbcImpl());
        // add new imp in there
    }

    public CalendarDao getCalendarDao() {
        return (CalendarDao) this.daos.get(this.type);

    }
}
