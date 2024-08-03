package org.example.coffeeshopposjavaeebackend.dao.custom;

import org.example.coffeeshopposjavaeebackend.dao.CrudDAO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProductDAO extends CrudDAO {
    String saveProduct(ProductDTO product , Connection connection) throws SQLException;
}
