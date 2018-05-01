package turing.controllor;

import turing.Model.User;
import turing.dao.factory.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(urlPatterns = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    public InitServlet() {
    }

    public void init() throws ServletException {
        UserDAOFactory.getInstance().setType("jdbc");
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
            String type = properties.getProperty("type");
            UserDAOFactory.getInstance().setType(type);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
