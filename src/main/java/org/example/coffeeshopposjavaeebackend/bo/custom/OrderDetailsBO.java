package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsBO extends SuperBO {
    List<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws Exception;
}
