package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO {
    String saveOrder(OrdersDTO order, Connection connection) throws SQLException;

    List<Orders> getAllOrders(Connection connection) throws Exception;
}
