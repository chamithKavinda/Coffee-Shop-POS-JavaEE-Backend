package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.CustomerBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        return customerDAO.saveCustomer(customer,connection);
    }

    public boolean deleteCustomer(String customerContact, Connection connection) throws Exception{
        return customerDAO.deleteCustomer(customerContact,connection);
    }

    public boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException {
        return customerDAO.updateCustomer(customerContact,customer,connection);
    }


    public List<CustomerDTO> getAllCustomer( Connection connection) throws Exception {
        List<Customer> customersList = customerDAO.getCustomer(connection);
        List<CustomerDTO> customerDTOS = new ArrayList<>();


        for (Customer customer : customersList) {
            customerDTOS.add(new CustomerDTO(
                    customer.getCustId(),
                    customer.getCustName(),
                    customer.getCustAddress(),
                    customer.getCustContact()
            ));
        }
        return customerDTOS;
    }
}
