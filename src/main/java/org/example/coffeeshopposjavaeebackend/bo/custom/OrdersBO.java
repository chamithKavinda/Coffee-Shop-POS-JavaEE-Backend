package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdersBO extends SuperBO {
    boolean purchseOrder(OrdersDTO order, Connection connection) throws Exception;

    String generateNewOrderID(Connection connection) throws SQLException;

}
