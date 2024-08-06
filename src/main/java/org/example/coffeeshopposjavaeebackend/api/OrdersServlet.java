package org.example.coffeeshopposjavaeebackend.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orders",loadOnStartup = 2)
public class OrdersServlet extends HttpServlet {
    Connection connection;

    static Logger logger = LoggerFactory.getLogger(CustomerServlet.class);
    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
            logger.info("Connection initialized",this.connection);
        } catch (SQLException | NamingException e){
            logger.error("DB connection not init");
            e.printStackTrace();
        }
    }


}
