package turing.dao.impl;

import turing.Model.MyCalendar;
import turing.dao.CalendarDao;
import turing.dao.DAO;

import java.sql.SQLException;

public class CalendarDaoJdbcImpl extends DAO<MyCalendar> implements CalendarDao {
    @Override
    public void addUp(String week) throws SQLException {
        String sql = "update calendar set up = up + 1 where week = ?";
        update(sql, Integer.parseInt(week));
    }

    @Override
    public void addDown(String week) throws SQLException {
        String sql = "update calendar set down = down + 1 where week = ?";
        update(sql, Integer.parseInt(week));
    }

    @Override
    public MyCalendar getByWeek(String week) throws SQLException {
        String sql = "select * from calendar where week = ?";
        return get(sql, Integer.parseInt(week));
    }


}
