package turing.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turing.Model.MyCalendar;
import turing.dao.CalendarDao;
import turing.dao.impl.CalendarDaoJdbcImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CalendarDaoJdbcImplTest {
    private static CalendarDao calendarDao;
    private static int up;
    private static int down;
    @BeforeAll
    static void setUp() {
        calendarDao = new CalendarDaoJdbcImpl();
    }

    @Test
    void addUp() throws SQLException {
        calendarDao.addUp("12");
        MyCalendar calendar = calendarDao.getByWeek("12");
        int upNow = Integer.parseInt(calendar.getUp());
        assertEquals(up+1, upNow);
    }

    @Test
    void addDown() throws SQLException {
        calendarDao.addDown("12");
        MyCalendar calendar = calendarDao.getByWeek("12");
        int downNow = Integer.parseInt(calendar.getDown());
        assertEquals(down+1, downNow);
    }

    @Test
    @BeforeEach
    void getByWeek() throws SQLException {
        MyCalendar calendar = calendarDao.getByWeek("12");
        up = Integer.parseInt(calendar.getUp());
        down = Integer.parseInt(calendar.getDown());
    }

}