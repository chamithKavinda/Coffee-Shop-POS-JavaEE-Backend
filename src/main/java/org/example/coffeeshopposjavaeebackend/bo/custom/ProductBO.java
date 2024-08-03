package org.example.coffeeshopposjavaeebackend.bo.custom;

import org.example.coffeeshopposjavaeebackend.bo.SuperBO;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;

import java.sql.Connection;

public interface ProductBO extends SuperBO {
    String saveProduct(ProductDTO product, Connection connection) throws Exception;
    boolean deleteProduct(String proId, Connection connection) throws Exception;
}
