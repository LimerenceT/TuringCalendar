package turing.controllor;


import turing.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;

@WebServlet(urlPatterns = {"/TuringCalendar"})
public class DateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String next = request.getParameter("next");
        String last = request.getParameter("last");
        if (next != null) {
            request.setAttribute("week", String.format("%02d", Integer.parseInt(next)));

        }
        else if (last != null) {

            request.setAttribute("week", String.format("%02d", Integer.parseInt(last)));
        }
        else {
            //        PrintWriter writer = response.getWriter();
            Calendar now = Calendar.getInstance();
            now.setFirstDayOfWeek(Calendar.MONDAY);
            //这里不设置content-type会导致中文乱码
            //writer.println("week:" + now.get(Calendar.WEEK_OF_YEAR));
            String week = (now.get(Calendar.WEEK_OF_YEAR) + 7) + "";
            request.setAttribute("week", week);
        }
        HttpSession httpSession = request.getSession();
        User user  = (User)httpSession.getAttribute("user");
        String info;
        String url;
        String login_info;
        if (user != null) {
            info = "欢迎回来," + user.getUsername();
            url = "logout.do";
            login_info = "退出登录";
        }
        else {
            info = "未登录";
            url = "login.jsp";
            login_info = "登录";
        }
        request.setAttribute("info", info);
        request.setAttribute("url", url);
        request.setAttribute("login_info", login_info);


        request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp").forward(request, response);


    }
}

