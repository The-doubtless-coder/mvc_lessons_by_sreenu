package org.rest.incorporated;

import org.rest.incorporated.model.GeneralResponse;
import org.rest.incorporated.model.ProductRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String postUri = "http://localhost:8080/springrestsreenu_war_exploded/api//v1.prod/create/product";
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductCode("xyzrr");
        productRequest.setName("anteerr");
        productRequest.setQuantity(1);
        productRequest.setPrice(22.22F);
        productRequest.setDescription("it is within my will");
        productRequest.setImageUrl("http://www.image.com/img/iir.png");
        ResponseEntity<GeneralResponse> generalResponseResponseEntity =
                restTemplate.postForEntity(postUri, productRequest, GeneralResponse.class);
        GeneralResponse body = generalResponseResponseEntity.getBody();
        System.out.println(body);
        HttpHeaders headers = generalResponseResponseEntity.getHeaders();
        System.out.println(headers);
        HttpStatus statusCode = generalResponseResponseEntity.getStatusCode();
        System.out.println(statusCode);
    }
}
