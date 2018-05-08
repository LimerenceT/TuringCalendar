package turing.controllor;

import turing.dao.factory.CalendarDAOFactory;
import turing.dao.factory.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(urlPatterns = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    public InitServlet() {
    }

    public void init() throws ServletException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
            String type = properties.getProperty("type");
            UserDAOFactory.getInstance().setType(type);
            CalendarDAOFactory.getInstance().setType(type);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
