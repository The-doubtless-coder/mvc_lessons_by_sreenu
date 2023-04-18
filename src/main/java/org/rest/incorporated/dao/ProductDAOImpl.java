package org.rest.incorporated.dao;

import org.rest.incorporated.exceptions.ProductAlreadyExistsException;
import org.rest.incorporated.exceptions.ProductNotFoundException;
import org.rest.incorporated.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("prod_DAO")
public class ProductDAOImpl implements ProductDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addProduct(Product product) throws ProductAlreadyExistsException {
        int update;
        String insertProductQuery = "INSERT INTO products_tb" +
                "(product_code, name, quantity, price, image_url, description) VALUES (?,?,?,?,?,?)";
        try {
            update = jdbcTemplate.update(insertProductQuery,
                    product.getProductCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getImageUrl(),
                    product.getDescription());
        } catch (Exception e) {
            System.err.println(e.getClass() + "::" + e.getMessage());
            throw new ProductAlreadyExistsException(e.getMessage());
        }
        if (update > 0) {
            return true;
        } else
            return false;
    }

    @Override
    public Product getProductById(String productCode) throws ProductNotFoundException {
        String getProductByIdQuery = "SELECT * FROM products_tb WHERE product_code=?";
        try {
            Product product = jdbcTemplate.queryForObject(getProductByIdQuery,
                    new Object[]{productCode},
                    new BeanPropertyRowMapper<>(Product.class));
            return product;
        } catch (Exception e) {
//            if(e instanceof EmptyResultDataAccessException){
//                throw new  EmptyResultDataAccessException("Product with that code does not exist", 1);
//            }
            System.err.println(e.getClass() + "::" + e.getMessage());
            throw new ProductNotFoundException(e.getClass() + "::::" + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> productList;
        String getAllProductsQuery = "SELECT product_id, product_code, " +
                "name, price, quantity, description, image_url  FROM products_tb";
        try {
            productList = jdbcTemplate.query(getAllProductsQuery, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt(1));
                    product.setProductCode(resultSet.getString(2));
                    product.setName(resultSet.getString(3));
                    product.setPrice(resultSet.getFloat(4));
                    product.setQuantity(resultSet.getInt(5));
                    product.setDescription(resultSet.getString(6));
                    product.setImageUrl(resultSet.getString(7));
                    return product;
                }
            });
        }catch (Exception e){
            System.err.println(e.getClass() + "::" + e.getMessage());
            throw new ProductNotFoundException(e.getClass()+ "::" + e.getMessage());
        }
        return productList;
    }

    @Override
    public boolean deleteProduct(String productCode) throws ProductNotFoundException {
        int update;
        String deleteProductQuery = "DELETE FROM products_tb WHERE product_code=?";
        try {
            update = jdbcTemplate.update(deleteProductQuery, productCode);
        } catch (Exception e) {
            System.err.println(e.getClass() + e.getMessage());
            throw new ProductNotFoundException(e.getClass() + "::::" + e.getMessage());
        }
        if (update > 0) {
            return true;
        } else
            return false;
    }
}
