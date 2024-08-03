package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    public static String SAVE_PRODUCT = "INSERT INTO product (pro_id,pro_name,price,category,quantity) VALUES(?,?,?,?,?)";
    public String saveProduct(ProductDTO product , Connection connection) throws SQLException{
        try{
            var sc = connection.prepareStatement(SAVE_PRODUCT);
            sc.setString(1,product.getPro_id());
            sc.setString(2,product.getPro_name());
            sc.setString(3,product.getPrice());
            sc.setString(4, product.getCategory());
            sc.setString(5, product.getQuantity());

            if(sc.executeUpdate() != 0){
                return "Product Save Successfully";
            }else {
                return "Failed to Save Product";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
