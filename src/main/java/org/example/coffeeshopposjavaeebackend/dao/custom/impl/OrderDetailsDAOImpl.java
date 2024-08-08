package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrderDetailsDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.util.SQLUtil;
import org.example.coffeeshopposjavaeebackend.dto.OrderDetailsDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.OrderDetails;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public static String GET_ALL_ORDERDETAILS = "SELECT * FROM orderdetails";
    public List<OrderDetails> getAllOrderDetails(Connection connection) throws Exception {
        try {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            var sc = connection.prepareStatement(GET_ALL_ORDERDETAILS);
            ResultSet rst = sc.executeQuery();
            ArrayList<OrderDetails> orderDetails = new ArrayList<>();

            while (rst.next()){
                orderDetails.add(new OrderDetails(
                        rst.getString("order_id"),
                        rst.getString("pro_Id"),
                        rst.getString("qty"),
                        rst.getString("unitPrice")
                ));
            }
            return orderDetails;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean save(Connection connection, OrderDetails entity) throws SQLException {
        String sql = "INSERT INTO orderdetails VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, connection, entity.getOrder_id(), entity.getPro_id(), entity.getQty(),entity.getUnitPrice());
    }
}
