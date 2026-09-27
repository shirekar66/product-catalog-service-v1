package com.scode.microservices.product_service.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
