package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.OrdersBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

import java.sql.Connection;

public class OrdersBOImpl implements OrdersBO {

    OrdersDAO ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS_DAO);

    @Override
    public String saveOrder(OrdersDTO order, Connection connection) throws Exception {
        return ordersDAO.saveOrder(order,connection);
    }

}
