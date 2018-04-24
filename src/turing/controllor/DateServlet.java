package turing.controllor;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

@WebServlet(urlPatterns = "/TuringCalendar")
public class DateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        PrintWriter writer = response.getWriter();
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        //这里不设置content-type会导致中文乱码
//        writer.println("week:" + now.get(Calendar.WEEK_OF_YEAR));
        String week = (now.get(Calendar.WEEK_OF_YEAR) + 7) + "";
        request.setAttribute("week", week);

        request.getRequestDispatcher("WEB-INF/jsp/calendar.jsp").forward(request, response);
    }
}

