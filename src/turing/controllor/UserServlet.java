package turing.controllor;

import turing.Model.Comment;
import turing.Model.MyCalendar;
import turing.Model.User;
import turing.dao.CalendarDao;
import turing.dao.CommentDao;
import turing.dao.UserDao;
import turing.dao.factory.CalendarDAOFactory;
import turing.dao.factory.CommentDaoFactory;
import turing.dao.factory.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;


@WebServlet(urlPatterns = "*.do")
public class UserServlet extends HttpServlet {
    private UserDao userDao = UserDAOFactory.getInstance().getUserDao();
    private CalendarDao calendarDao = CalendarDAOFactory.getInstance().getCalendarDao();
    private CommentDao commentDao = CommentDaoFactory.getInstance().getCommentDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //解决接受中文用户名显示乱码
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 3);

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String get_password = request.getParameter("md5_password");

        // sha-1 加密
        try {
            String password = new String(MessageDigest.getInstance("SHA-1").digest(get_password.getBytes()));
            User user = userDao.getByName(username);
            if (user != null) {
                request.setAttribute("message", "这个名字被注册过了");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                userDao.add(newUser);

                HttpSession session = request.getSession(true);
                session.setAttribute("user", newUser);
                response.sendRedirect("send.jsp");
            }


        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String get_password = request.getParameter("md5_password");

        // sha-1 加密
        try {
            String password = new String(MessageDigest.getInstance("SHA-1").digest(get_password.getBytes()));
            User user = userDao.getByName(username);
            if (user != null && user.getPassword().equals(password)) {
                request.setAttribute("message", "登录成功");

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                Cookie cookie = new Cookie("JSESSIONID",session.getId());
                cookie.setMaxAge(2*7*24*3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect("/now.do");


            } else {
                request.setAttribute("message", "密码或用户名错误！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                cookie.setValue(null);
                cookie.setMaxAge(0);// 立即销毁cookie
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
        now(request, response);

    }

    private void nextOrlast(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String week = request.getParameter("week");

        if (week != null) {
            request.setAttribute("week", String.format("%02d", Integer.parseInt(week)));
        }
        getCalendarInfo(request, response);
    }

    private void now(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        String week = (now.get(Calendar.WEEK_OF_YEAR) + 7) + "";
        request.setAttribute("week", week);
        getCalendarInfo(request, response);
    }

    private void getCalendarInfo(HttpServletRequest request, HttpServletResponse response){
        MyCalendar myCalendar = null;
        String week = (String) request.getAttribute("week");
        try {
            myCalendar = calendarDao.getByWeek(week);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String up = myCalendar != null ? myCalendar.getUp() : null;
        String down = myCalendar != null ? myCalendar.getDown() : null;

        request.setAttribute("up", up); //获取这一张日历的点赞和踩的数量
        request.setAttribute("down", down);

        HttpSession httpSession = request.getSession(); //通过session获取user来判断是否登录,登录就返回他对日历的一些操作信息
        User user  = (User)httpSession.getAttribute("user");

        boolean upState = false;
        boolean downState = false;
        List<Boolean> states;
        if (user != null) {
            try {
                states = userDao.state(userDao.getByName(user.getUsername()), week);
                upState = states.get(0);
                downState = states.get(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        List<Comment> comments = commentDao.queryByWeek(Integer.parseInt(week));//根据星期week获取这张日历的所有评论
        request.setAttribute("comments", comments);
        request.setAttribute("upState", upState);
        request.setAttribute("downState", downState);

        // 用于发现jsp页面上的报错
        try {
            request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void up(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String week = request.getParameter("week");
        String username = request.getParameter("name");
        List<Boolean> states = userDao.state(userDao.getByName(username), week);
        if (!(states.get(0) || states.get(1))) {
            try {
                calendarDao.addUp(week);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                User user = userDao.getByName(username);
                if (user != null) {
                    user.setLiked(week);
                    userDao.up(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        request.setAttribute("week", week);
        getCalendarInfo(request, response);

    }

    private void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String week = request.getParameter("week");
        String username = request.getParameter("name");
        List<Boolean> states = userDao.state(userDao.getByName(username), week);
        if (!(states.get(0) || states.get(1))) {
            try {
                calendarDao.addDown(week);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                User user = userDao.getByName(username);
                user.setDisliked(week);
                userDao.down(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        request.setAttribute("week", week);
        getCalendarInfo(request, response);

    }

    private void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String username = request.getParameter("username");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        String week = request.getParameter("week");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreate_time(time);
        comment.setUsername(username);
        comment.setWeek(Integer.parseInt(week));
        commentDao.save(comment);

    }
}
