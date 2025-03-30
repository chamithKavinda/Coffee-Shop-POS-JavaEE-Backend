package org.example.coffeeshopposjavaeebackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.coffeeshopposjavaeebackend.bo.BOFactory;
import org.example.coffeeshopposjavaeebackend.bo.custom.CustomerBO;
import org.example.coffeeshopposjavaeebackend.bo.custom.OrdersBO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orders",loadOnStartup = 2)
public class OrdersServlet extends HttpServlet {
    OrdersBO ordersBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS_BO);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received Post Request for Order");
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var write = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            OrdersDTO order = jsonb.fromJson(req.getReader(), OrdersDTO.class);

            boolean isSaved = ordersBO.purchseOrder(order, connection);
            if (isSaved){
                write.write("orderSaved");
            }else {
                write.write("not saved");
            }

//            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received GET request for all Orders");
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(ordersBO.generateNewOrderID(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
