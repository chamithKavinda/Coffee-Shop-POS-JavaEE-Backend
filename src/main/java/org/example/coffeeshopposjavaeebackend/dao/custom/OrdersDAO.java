package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO {
    boolean saveOrder(Orders order, Connection connection) throws SQLException;

    String generateNextId(Connection connection) throws SQLException;
}
