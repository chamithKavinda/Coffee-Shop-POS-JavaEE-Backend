package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.ProductBO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.ProductDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductBOImpl implements ProductBO {
    public String saveProduct(ProductDTO product, Connection connection) throws Exception {
        var productDAOImpl = new ProductDAOImpl();
        return productDAOImpl.saveProduct(product,connection);
    }

    public boolean deleteProduct(String proId, Connection connection) throws Exception {
        var productDAOImpl = new ProductDAOImpl();
        return productDAOImpl.deleteProduct(proId,connection);
    }

    public boolean updateProduct(String proId, ProductDTO product, Connection connection) throws SQLException {
        var productDAOImpl = new ProductDAOImpl();
        return productDAOImpl.updateProduct(proId,product,connection);
    }
}
