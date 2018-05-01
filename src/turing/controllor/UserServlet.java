package turing.controllor;

import turing.Model.User;
import turing.dao.UserDao;
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

@WebServlet(urlPatterns = "*.do")
public class UserServlet extends HttpServlet {
    private UserDao userDao = UserDAOFactory.getInstance().getUserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决接受中文用户名显示乱码
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.setAttribute("message", "登录成功");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                Cookie cookie = new Cookie("JSESSIONID",session.getId());
                cookie.setMaxAge(2*7*24*3600);
                cookie.setPath("/");
                response.addCookie(cookie);

                response.sendRedirect("/TuringCalendar");

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
        response.sendRedirect("/TuringCalendar");

    }
}
