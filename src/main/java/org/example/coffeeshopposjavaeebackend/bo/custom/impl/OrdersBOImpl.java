package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.OrdersBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrderDetailsDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.OrdersDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dto.OrderDetailsDTO;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;
import org.example.coffeeshopposjavaeebackend.entity.OrderDetails;
import org.example.coffeeshopposjavaeebackend.entity.Orders;
import org.example.coffeeshopposjavaeebackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS_DAO);

    ProductDAO productDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT_DAO);

    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS_DAO);

    @Override
    public boolean purchseOrder(OrdersDTO order, Connection connection) throws Exception {

        connection.setAutoCommit(false);

        Orders orders = new Orders(order.getOrder_id(),order.getDateAndTime(),order.getContact());

        boolean saveOrder = ordersDAO.saveOrder(orders,connection);
        if (!saveOrder){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailsDTO orderDetail : order.getOrderDetails()) {
            boolean orderDetailSaved = orderDetailsDAO.save(connection, new OrderDetails(
                    orderDetail.getOrder_id(),
                    orderDetail.getPro_id(),
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice()));
            if (!orderDetailSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Product product = productDAO.search(connection, orderDetail.getPro_id());

            product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) - Integer.parseInt(orderDetail.getQty())));

            boolean isUpdated = productDAO.update(connection, product);

            if (!isUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);

        return true;

    }

    @Override
    public String generateNewOrderID(Connection connection) throws SQLException {
        String lastOrderId = ordersDAO.generateNextId(connection);
        if (lastOrderId != null){
            String prefix = lastOrderId.substring(0, 1);
            int number = Integer.parseInt(lastOrderId.substring(1));
            number++;
            String formattedNumber = String.format("%03d", number);
            return prefix + formattedNumber;
        }
        return "O001";
    }

}
