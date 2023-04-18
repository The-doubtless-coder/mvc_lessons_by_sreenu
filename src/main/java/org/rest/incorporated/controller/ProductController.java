package org.rest.incorporated.controller;

import io.swagger.annotations.Api;
import org.rest.incorporated.exceptions.ProductAlreadyExistsException;
import org.rest.incorporated.exceptions.ProductNotFoundException;
import org.rest.incorporated.model.GeneralResponse;
import org.rest.incorporated.model.Product;
import org.rest.incorporated.model.ProductRequest;
import org.rest.incorporated.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1.prod")
@Api(value = "product controller", produces = "JSON/XML", consumes = "JSON/XML", protocols = "HTTP")
public class ProductController {
    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allProducts",
            produces = {"application/json", "application/xml"})
    public ResponseEntity getAllProducts() {
        List<Product> allProducts;
        try {
            allProducts = productService.getAllProducts();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GeneralResponse
                    (HttpStatus.EXPECTATION_FAILED,
                            "/v1.prod/allProducts",
                            LocalDateTime.now().toString(),
                            null, e.getMessage().toString(),
                            null));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allProducts);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{productCode}",
            produces = {"application/json", "application/xml"})
    public ResponseEntity getProductById(@PathVariable("productCode") String productCode) {
        Product product;
        try {
            product = productService.getProduct(productCode);
        } catch (ProductNotFoundException e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GeneralResponse(
                    HttpStatus.EXPECTATION_FAILED, "/v1.prod/product/{productCode}",
                    LocalDateTime.now().toString(), null, e.getMessage().toString(), null
            ));
        }
        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/product",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequest request) {
        boolean b;
        try {
            b = productService.saveProduct(request);
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GeneralResponse(
                    HttpStatus.EXPECTATION_FAILED, "/v1.prod/create/product",
                    LocalDateTime.now().toString(), null, e.getMessage().toString(), null
            ));
        }
        return ResponseEntity.ok(new GeneralResponse(HttpStatus.ACCEPTED,
                "/v1.prod/create/product", LocalDateTime.now().toString(),
                null, "Product Saved::" + b, null ));
    }
}
