package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerBO extends SuperBO {
    String saveCustomer(CustomerDTO customer, Connection connection)throws Exception;

    boolean deleteCustomer(String customerContact, Connection connection) throws Exception;
}