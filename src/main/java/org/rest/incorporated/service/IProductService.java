package org.rest.incorporated.service;

import org.rest.incorporated.exceptions.ProductAlreadyExistsException;
import org.rest.incorporated.exceptions.ProductNotFoundException;
import org.rest.incorporated.model.Product;
import org.rest.incorporated.model.ProductRequest;

import java.util.List;

public interface IProductService {
    public boolean saveProduct(ProductRequest productRequest) throws ProductAlreadyExistsException;
    public Product getProduct(String productCode) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws ProductNotFoundException;
    public boolean deleteProduct(String productCode) throws ProductNotFoundException;
}
