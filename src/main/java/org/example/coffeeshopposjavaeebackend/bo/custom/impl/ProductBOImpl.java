package org.example.coffeeshopposjavaeebackend.bo.custom.impl;

import org.example.coffeeshopposjavaeebackend.bo.custom.ProductBO;
import org.example.coffeeshopposjavaeebackend.dao.DAOFactory;
import org.example.coffeeshopposjavaeebackend.dao.custom.ProductDAO;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.CustomerDAOImpl;
import org.example.coffeeshopposjavaeebackend.dao.custom.impl.ProductDAOImpl;
import org.example.coffeeshopposjavaeebackend.dto.ProductDTO;
import org.example.coffeeshopposjavaeebackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT_DAO);

    public String saveProduct(ProductDTO product, Connection connection) throws Exception {
        return productDAO.saveProduct(product,connection);
    }

    public boolean deleteProduct(String proId, Connection connection) throws Exception {
        return productDAO.deleteProduct(proId,connection);
    }

    public boolean updateProduct(String proId, ProductDTO product, Connection connection) throws SQLException {
        return productDAO.updateProduct(proId,product,connection);
    }

    public List<ProductDTO> getAllProduct(Connection connection) throws Exception{
        List<Product> productList = productDAO.getAllProduct(connection);
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : productList){
            productDTOS.add(new ProductDTO(
                    product.getPro_id(),
                    product.getPro_name(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getQuantity()
            ));
        }
        return productDTOS;
    }
}
