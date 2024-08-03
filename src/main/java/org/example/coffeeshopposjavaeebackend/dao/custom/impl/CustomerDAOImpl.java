package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.CustomerDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    public static String SAVE_CUSTOMER = "INSERT INTO customer (cust_id,cust_name,address,contact) VALUES(?,?,?,?)";

    public static String DELETE_CUSTOMER = "DELETE FROM customer where contact=?";

    public static String UPDATE_CUSTOMER = "UPDATE customer SET cust_id=?,cust_name=?,address=? WHERE contact=?";

    public static String GET_CUSTOMER = "SELECT * FROM customer WHERE contact = ?";
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

    public CustomerDTO getCustomer(String customerContact, Connection connection) throws Exception {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var sc = connection.prepareStatement(GET_CUSTOMER);
            sc.setString(1,customerContact);
            var rst = sc.executeQuery();
            while (rst.next()){
                customerDTO.setCustId(rst.getString("custId"));
                customerDTO.setCustName(rst.getString("custName"));
                customerDTO.setCustAddress(rst.getString("custAddress"));
                customerDTO.setCustContact(rst.getString("custContact"));
            }
            return customerDTO;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
