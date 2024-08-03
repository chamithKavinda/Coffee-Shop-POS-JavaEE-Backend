package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.ProductBO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.ProductDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;

public class ProductBOImpl implements ProductBO {
    public String saveProduct(ProductDTO product, Connection connection) throws Exception {
        var productDAOImpl = new ProductDAOImpl();
        return productDAOImpl.saveProduct(product,connection);
    }
}
