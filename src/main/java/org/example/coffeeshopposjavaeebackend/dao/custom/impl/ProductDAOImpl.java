package org.example.coffeeshopposjavaeebackend.dao.custom.impl;

import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.util.SQLUtil;
import org.example.coffeeshopposjavaeebackend.dto.CustomerDTO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;
import org.example.coffeeshopposjavaeebackend.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public static String SAVE_PRODUCT = "INSERT INTO product (pro_id,pro_name,price,category,quantity) VALUES(?,?,?,?,?)";

    public static String DELETE_PRODUCT = "DELETE FROM product where pro_id=?";

    public static String UPDATE_PRODUCT = "UPDATE product SET pro_name=?, price=?, category=?, quantity=? WHERE pro_id=?";

    public static String GET_PRODUCT = "SELECT * FROM product";
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
            sc.setString(1,product.getPro_name());
            sc.setString(2,product.getPrice());
            sc.setString(3,product.getCategory());
            sc.setString(4,product.getQuantity());
            sc.setString(5,product.getPro_id());
            return sc.executeUpdate() !=0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public List<Product> getAllProduct(Connection connection) throws SQLException {
        try {
            ProductDTO productDTO = new ProductDTO();
            var sc = connection.prepareStatement(GET_PRODUCT);
            ResultSet rst = sc.executeQuery();
            ArrayList<Product> products = new ArrayList<>();

            while (rst.next()){
                products.add(new Product(
                        rst.getString("pro_id"),
                        rst.getString("pro_name"),
                        rst.getString("price"),
                        rst.getString("category"),
                        rst.getString("quantity")));
            }
            return products;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Product search(Connection connection, String proId) throws SQLException {
        String sql =  "SELECT * FROM product WHERE pro_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, connection, proId);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public boolean update(Connection connection, Product product) throws SQLException {
        String sql = "UPDATE product SET pro_name=?, price=?, category=?, quantity=? WHERE pro_id=?";
        return SQLUtil.execute(sql, connection,
                product.getPro_name(),
                product.getPrice(),
                product.getCategory(),
                product.getQuantity(),
                product.getPro_id()
                );
    }
}
