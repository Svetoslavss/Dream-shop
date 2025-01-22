package com.academy.dream_shop.exceptions;

import java.util.Objects;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
