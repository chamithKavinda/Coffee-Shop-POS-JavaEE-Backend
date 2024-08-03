package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO {
    String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException;

    boolean deleteCustomer(String customerContact, Connection connection) throws SQLException;

    boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException;

    CustomerDTO getCustomer(String customerContact, Connection connection) throws Exception;
}
