package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    public static String SAVE_PRODUCT = "INSERT INTO product (pro_id,pro_name,price,category,quantity) VALUES(?,?,?,?,?)";

    public static String DELETE_PRODUCT = "DELETE FROM product where pro_id=?";

    public static String UPDATE_PRODUCT = "UPDATE product SET pro_name=?, price=?, category=?, quantity=? WHERE pro_id=?";

    public static String GET_PRODUCT = "SELECT * FROM product WHERE pro_id = ?";
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

    public boolean deleteProduct(String proId, Connection connection) throws SQLException {
        var sc = connection.prepareStatement(DELETE_PRODUCT);
        sc.setString(1,proId);
        return sc.executeUpdate() !=0;
    }

    public boolean updateProduct(String proId, ProductDTO product, Connection connection) throws SQLException {
        try{
            var sc = connection.prepareStatement(UPDATE_PRODUCT);
            sc.setString(1,product.getPro_id());
            sc.setString(2,product.getPro_name());
            sc.setString(3,product.getPrice());
            sc.setString(4,product.getCategory());
            sc.setString(5,product.getQuantity());
            return sc.executeUpdate() !=0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public ProductDTO getProduct(String proId, Connection connection) throws SQLException {
        try {
            ProductDTO productDTO = new ProductDTO();
            var sc = connection.prepareStatement(GET_PRODUCT);
            sc.setString(1,proId);
            var rst = sc.executeQuery();
            while (rst.next()){
                productDTO.setPro_id(rst.getString("pro_id"));
                productDTO.setPro_name(rst.getString("pro_name"));
                productDTO.setPrice(rst.getString("price"));
                productDTO.setCategory(rst.getString("category"));
                productDTO.setQuantity(rst.getString("quantity"));
            }
            return productDTO;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
