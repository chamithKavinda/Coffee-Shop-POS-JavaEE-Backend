package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;
import org.example.coffeeshopposjavaeebackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO {
    String saveProduct(ProductDTO product , Connection connection) throws SQLException;

    boolean deleteProduct(String proId, Connection connection) throws SQLException;

    boolean updateProduct(String proId, ProductDTO product, Connection connection) throws SQLException;

    List<Product> getAllProduct(Connection connection) throws SQLException;

    Product search(Connection connection, String proId) throws SQLException;

    boolean update(Connection connection, Product product) throws SQLException;
}
