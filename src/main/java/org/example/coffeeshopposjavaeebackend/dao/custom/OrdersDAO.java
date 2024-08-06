package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO {
    String saveOrder(OrdersDTO order, Connection connection) throws SQLException;
}
