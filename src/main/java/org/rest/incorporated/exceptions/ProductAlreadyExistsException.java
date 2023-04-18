package org.rest.incorporated.exceptions;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
