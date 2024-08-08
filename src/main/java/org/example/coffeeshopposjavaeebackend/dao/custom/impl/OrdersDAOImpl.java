package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.util.SQLUtil;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (order_id,dateAndTime,contact) VALUES(?,?,?)";


    @Override
    public boolean saveOrder(Orders order, Connection connection) throws SQLException {

        try{
            var sc = connection.prepareStatement(SAVE_ORDER);
            sc.setString(1,order.getOrder_id());
            sc.setString(2, String.valueOf(order.getDateAndTime()));
            sc.setString(3,order.getContact());
            System.out.println(order.getContact());
            if(sc.executeUpdate() != 0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public String generateNextId(Connection connection) throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql, connection);
        if (resultSet.next()) {
            String order_id = resultSet.getString(1);
            return order_id;
        }

        return null;
    }
}
