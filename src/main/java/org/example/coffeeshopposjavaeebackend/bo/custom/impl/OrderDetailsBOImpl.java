package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.OrderDetailsBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrderDetailsDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dto.OrderDetailsDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.OrderDetails;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS_DAO);


    public List<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws Exception {
        List<OrderDetails> OrderDetailsList = orderDetailsDAO.getAllOrderDetails(connection);
        List<OrderDetailsDTO> OrderDetailsDTOS = new ArrayList<>();


        for (OrderDetails orderDetails : OrderDetailsList) {
            OrderDetailsDTOS.add(new OrderDetailsDTO(
                    orderDetails.getOrder_id(),
                    orderDetails.getPro_id(),
                    orderDetails.getQty(),
                    orderDetails.getUnitPrice()
            ));
        }
        return OrderDetailsDTOS;
    }

}
