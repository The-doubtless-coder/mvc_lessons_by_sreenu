package org.rest.incorporated.dao;

import org.rest.incorporated.exceptions.ProductAlreadyExistsException;
import org.rest.incorporated.exceptions.ProductNotFoundException;
import org.rest.incorporated.model.Product;

import java.util.List;

public interface ProductDAO {
    public boolean addProduct(Product product)throws  ProductAlreadyExistsException;
    public Product getProductById(String productCode) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws ProductNotFoundException;
    public boolean deleteProduct(String productCode) throws ProductNotFoundException;
}
