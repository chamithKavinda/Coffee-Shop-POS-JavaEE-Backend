package org.example.coffeeshopposjavaeebackend.dao;

import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER_DAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER_DAO:
                return (T) new CustomerDAOImpl();
            default:
                return null;
        }
    }

}
