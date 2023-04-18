package org.rest.incorporated.service;

import org.rest.incorporated.dao.ProductDAO;
import org.rest.incorporated.exceptions.ProductAlreadyExistsException;
import org.rest.incorporated.exceptions.ProductNotFoundException;
import org.rest.incorporated.model.Product;
import org.rest.incorporated.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prod_service")
public class ProductServiceImpl implements IProductService{
    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean saveProduct(ProductRequest productRequest) throws ProductAlreadyExistsException {
        Product product = new Product();
        product.setProductCode(productRequest.getProductCode());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        boolean b = productDAO.addProduct(product);
        return b;
    }

    @Override
    public Product getProduct(String productCode) throws ProductNotFoundException {
        Product productById = productDAO.getProductById(productCode);
        return productById;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> allProducts = productDAO.getAllProducts();
        return allProducts;
    }

    @Override
    public boolean deleteProduct(String productCode) throws ProductNotFoundException {
        boolean b = productDAO.deleteProduct(productCode);
        return b;
    }
}
