package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;
import java.util.List;

public interface OrdersBO extends SuperBO {
    String saveOrder(OrdersDTO order, Connection connection) throws Exception;

    List<OrdersDTO> getAllOrders(Connection connection) throws Exception;
}
