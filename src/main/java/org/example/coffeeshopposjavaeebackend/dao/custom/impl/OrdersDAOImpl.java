package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdersDAOImpl implements OrdersDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (order_id,dateAndTime,contact) VALUES(?,?,?)";

    @Override
    public String saveOrder(OrdersDTO order, Connection connection) throws SQLException {

        try{
            var sc = connection.prepareStatement(SAVE_ORDER);
            sc.setString(1,order.getOrder_id());
            sc.setString(2, String.valueOf(order.getDateAndTime()));
            sc.setString(3,order.getCustContact());
            System.out.println(order.getCustContact());
            if(sc.executeUpdate() != 0){
                return "Order Save Successfully";
            }else {
                return "Failed to Save Student";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
