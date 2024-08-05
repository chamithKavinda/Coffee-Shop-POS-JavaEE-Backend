package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    String saveCustomer(CustomerDTO customer, Connection connection)throws Exception;

    boolean deleteCustomer(String customerContact, Connection connection) throws Exception;

    boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException;

    List<CustomerDTO> getAllCustomer(Connection connection) throws Exception;
}
