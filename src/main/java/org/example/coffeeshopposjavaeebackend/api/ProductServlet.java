package org.example.coffeeshopposjavaeebackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.coffeeshopposjavaeebackend.bo.BOFactory;
import org.example.coffeeshopposjavaeebackend.bo.custom.ProductBO;
import org.example.coffeeshopposjavaeebackend.bo.custom.impl.CustomerBOImpl;
import org.example.coffeeshopposjavaeebackend.bo.custom.impl.ProductBOImpl;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/product",loadOnStartup = 2)
public class ProductServlet extends HttpServlet {

    ProductBO productBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT_BO);

    static Logger logger = LoggerFactory.getLogger(ProductServlet.class);
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
        logger.debug("Received POST request for Products");
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var write = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            ProductDTO product = jsonb.fromJson(req.getReader(), ProductDTO.class);

            write.write(productBO.saveProduct(product,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
//            write.write("Product save Sucessfully");
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received DELETE request for Products");
        try(var write = resp.getWriter()){
            var pro_id = req.getParameter("pro_id");

            if (productBO.deleteProduct(pro_id,connection)){
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                write.write("Product Delete Sucessfully");
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
        logger.debug("Received Update request for Products");
        try (var write = resp.getWriter()){
            var pro_id = req.getParameter("pro_id");
            Jsonb jsonb = JsonbBuilder.create();
            ProductDTO product = jsonb.fromJson(req.getReader(), ProductDTO.class);
            System.out.println(pro_id);
            System.out.println(product.getQuantity());

            if(productBO.updateProduct(pro_id,product,connection)){
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                write.write("Product Update Sucessfully");
            }else {
                write.write("Update Failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received Get All request for Products");
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(productBO.getAllProduct(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
