package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.Customer;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (order_id,dateAndTime,contact) VALUES(?,?,?)";

    public static String GET_ALL_ORDERS = "SELECT * FROM orders ";

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

    public List<Orders> getAllOrders(Connection connection) throws Exception {
        try {
            OrdersDTO ordersDTO = new OrdersDTO();
            var sc = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet rst = sc.executeQuery();
            ArrayList<Orders> orders = new ArrayList<>();

            while (rst.next()){
                orders.add(new Orders(
                        rst.getString("order_id"),
                        rst.getString("dateAndTime"),
                        rst.getString("custContact")
                        ));
            }
            return orders;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
