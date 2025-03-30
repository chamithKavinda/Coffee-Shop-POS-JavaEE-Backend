package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public static String SAVE_CUSTOMER = "INSERT INTO customer (cust_id,cust_name,address,contact) VALUES(?,?,?,?)";

    public static String DELETE_CUSTOMER = "DELETE FROM customer where contact=?";

    public static String UPDATE_CUSTOMER = "UPDATE customer SET cust_id=?,cust_name=?,address=? WHERE contact=?";

    public static String GET_CUSTOMER = "SELECT * FROM customer";
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException {
        try{

            var sc = connection.prepareStatement(SAVE_CUSTOMER);
            sc.setString(1,customer.getCustId());
            sc.setString(2,customer.getCustName());
            sc.setString(3,customer.getCustAddress());
            sc.setString(4, customer.getCustContact());
            if(sc.executeUpdate() != 0){
                return "Customer Save Successfully";
            }else {
                return "Failed to Save Student";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean deleteCustomer(String contact, Connection connection) throws SQLException {
        var sc = connection.prepareStatement(DELETE_CUSTOMER);
        sc.setString(1,contact);

        return sc.executeUpdate() !=0;
    }

    @Override
    public boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException{
        try{
            var sc = connection.prepareStatement(UPDATE_CUSTOMER);
            sc.setString(1,customer.getCustId());
            sc.setString(2,customer.getCustName());
            sc.setString(3,customer.getCustAddress());
            sc.setString(4, customer.getCustContact());
            return sc.executeUpdate() !=0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public List<Customer> getCustomer(Connection connection) throws Exception {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var sc = connection.prepareStatement(GET_CUSTOMER);
            ResultSet rst = sc.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();

            while (rst.next()){
                customers.add(new Customer(
                        rst.getString("cust_id"),
                        rst.getString("cust_name"),
                        rst.getString("address"),
                        rst.getString("contact")));
            }
            return customers;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
