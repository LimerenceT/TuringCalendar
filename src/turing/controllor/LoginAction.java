package turing.controllor;

import turing.Model.User;
import turing.dao.implement.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String get_password = request.getParameter("md5_password");

        // sha-1 加密
        try {
            String password = new String(MessageDigest.getInstance("SHA-1").digest(get_password.getBytes()));
            UserDaoImpl dao = new UserDaoImpl();
            User user = dao.getByName(username);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.setAttribute("login", false);
                request.getRequestDispatcher("/TuringCalendar").forward(request, response);
            }
            else {
                request.setAttribute("login", true);
                request.getRequestDispatcher("/TuringCalendar").forward(request, response);
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
//        request.getRequestDispatcher("/TuringCalendar").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
