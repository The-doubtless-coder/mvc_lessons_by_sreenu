package org.rest.incorporated.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
