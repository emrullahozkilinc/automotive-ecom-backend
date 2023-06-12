package com.fmss.automotiveecombackend.exception.exception_classes;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }
}
