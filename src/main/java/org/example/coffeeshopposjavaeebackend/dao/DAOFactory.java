package org.example.coffeeshopposjavaeebackend.dao;

import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.OrderDetailsDAOImpl;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.OrdersDAOImpl;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.ProductDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.OrdersDTO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER_DAO,
        PRODUCT_DAO,
        ORDERS_DAO,
        ORDERDETAILS_DAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER_DAO:
                return (T) new CustomerDAOImpl();
            case PRODUCT_DAO:
                return (T) new ProductDAOImpl();
            case ORDERS_DAO:
                return (T) new OrdersDAOImpl();
            case ORDERDETAILS_DAO:
                return (T) new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }

}
