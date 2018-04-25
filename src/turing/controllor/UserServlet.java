package turing.controllor;

import turing.Model.User;
import turing.dao.UserDao;
import turing.dao.impl.UserDaoJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "*.do")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决接受中文用户名显示乱码
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 3);
        System.out.println(methodName);

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
            UserDao dao = new UserDaoJdbcImpl();
            User user = dao.getByName(username);
            if (user != null) {
                request.setAttribute("message", "这个名字被注册过了");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                dao.add(newUser);
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
            UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
            User user = dao.getByName(username);
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.setAttribute("message", "登录成功");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

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
        response.sendRedirect("/TuringCalendar");

    }
}