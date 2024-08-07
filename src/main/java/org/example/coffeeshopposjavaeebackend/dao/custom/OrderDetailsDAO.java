package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.entity.OrderDetails;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO {
    List<OrderDetails> getAllOrderDetails(Connection connection) throws Exception;
}
