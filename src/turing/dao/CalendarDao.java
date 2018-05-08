package turing.dao;

import turing.Model.MyCalendar;

import java.sql.SQLException;

public interface CalendarDao {
    //点赞
    public void addUp(String week) throws SQLException;

    //踩
    public void addDown(String week) throws SQLException;

    //查询点赞数量
    public MyCalendar getByWeek(String week) throws SQLException;
}
