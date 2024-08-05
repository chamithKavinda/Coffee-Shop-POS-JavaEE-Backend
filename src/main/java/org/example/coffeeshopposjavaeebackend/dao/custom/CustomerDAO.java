package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO {
    String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException;

    boolean deleteCustomer(String customerContact, Connection connection) throws SQLException;

    boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException;

    List<Customer> getCustomer(Connection connection) throws Exception;
}
