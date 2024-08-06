package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.OrdersBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.Customer;
import org.example.coffeeshopposjavaeebackend.entity.Orders;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    OrdersDAO ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS_DAO);

    @Override
    public String saveOrder(OrdersDTO order, Connection connection) throws Exception {
        return ordersDAO.saveOrder(order,connection);
    }

    public List<OrdersDTO> getAllOrders(Connection connection) throws Exception {
        List<Orders> OrdersList = ordersDAO.getAllOrders(connection);
        List<OrdersDTO> OrderDTOS = new ArrayList<>();


        for (Orders orders : OrdersList) {
            OrderDTOS.add(new OrdersDTO(
                    orders.getOrder_id(),
                    orders.getDateAndTime(),
                    orders.getCustContact()
            ));
        }
        return OrderDTOS;
    }

}
