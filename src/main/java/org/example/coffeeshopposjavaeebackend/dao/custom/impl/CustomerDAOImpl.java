package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    public static String SAVE_CUSTOMER = "INSERT INTO customer (cust_id,cust_name,address,contact) VALUES(?,?,?,?)";

    public static String DELETE_CUSTOMER = "DELETE FROM customer where contact=?";

    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException {
        try{

            var sc = connection.prepareStatement(SAVE_CUSTOMER);
            sc.setString(1,customer.getCustId());
            sc.setString(2,customer.getCustName());
            sc.setString(3,customer.getCustAddress());
            sc.setString(4, customer.getCustContact());
            if(sc.executeUpdate() != 0){
                return "Student Save Successfully";
            }else {
                return "Failed to Save Student";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public boolean deleteCustomer(String contact, Connection connection) throws SQLException {
        var sc = connection.prepareStatement(DELETE_CUSTOMER);
        sc.setString(1,contact);
        return sc.executeUpdate() !=0;
    }
}
