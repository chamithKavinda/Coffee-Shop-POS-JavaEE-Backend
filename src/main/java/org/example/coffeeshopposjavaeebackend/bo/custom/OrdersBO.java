package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;

public interface OrdersBO extends SuperBO {
    String saveOrder(OrdersDTO order, Connection connection) throws Exception;
}
