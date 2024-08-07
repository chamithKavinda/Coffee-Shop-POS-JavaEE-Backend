package org.example.coffeeshopposjavaeebackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.coffeeshopposjavaeebackend.bo.BOFactory;
import org.example.coffeeshopposjavaeebackend.bo.custom.OrderDetailsBO;
import org.example.coffeeshopposjavaeebackend.bo.custom.OrdersBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orderDetails",loadOnStartup = 2)
public class OrderDetailsServlet extends HttpServlet {

    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAILS_BO);
    static Logger logger = LoggerFactory.getLogger(CustomerServlet.class);
    Connection connection;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received GET request for all OrderDetails");
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(orderDetailsBO.getAllOrderDetails(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
