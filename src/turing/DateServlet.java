package turing;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;


@WebServlet(urlPatterns = "/TuringCalendar/", asyncSupported = true)
public class DateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        writer.println("week:" + now.get(Calendar.WEEK_OF_YEAR));
        String week = (now.get(Calendar.WEEK_OF_YEAR) + 7) + "";
        response.sendRedirect("../../calendar.jsp?week=" + week);




    }
}

