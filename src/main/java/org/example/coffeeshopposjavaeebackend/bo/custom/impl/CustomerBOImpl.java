package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.CustomerBO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        var customerDAOImpl = new CustomerDAOImpl();
        return customerDAOImpl.saveCustomer(customer,connection);
    }

    public boolean deleteCustomer(String customerContact, Connection connection) throws Exception{
        var customerDAOImpl = new CustomerDAOImpl();
        return customerDAOImpl.deleteCustomer(customerContact,connection);
    }
}
