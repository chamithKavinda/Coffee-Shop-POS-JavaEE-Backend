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
import org.example.coffeeshopposjavaeebackend.bo.custom.impl.CustomerBOImpl;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer",loadOnStartup = 2)
public class CustomerServlet extends HttpServlet {

    CustomerBO customerBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_BO);

    static Logger logger = LoggerFactory.getLogger(CustomerServlet.class);
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
            logger.info("Connection initialized",this.connection);
        } catch (SQLException |NamingException e){
            logger.error("DB connection not init");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var write = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customer = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            write.write(customerBO.saveCustomer(customer,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(customerBO.getAllCustomer(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var write = resp.getWriter()){
            var customerContact = req.getParameter("contact");

            if (customerBO.deleteCustomer(customerContact,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                write.write("Delete Failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var write = resp.getWriter()){
            var customerContact = req.getParameter("contact");
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customer = jsonb.fromJson(req.getReader(),CustomerDTO.class);

            if(customerBO.updateCustomer(customerContact,customer,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                write.write("Update Failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider logging this exception
        }
    }
}
