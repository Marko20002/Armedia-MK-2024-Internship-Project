package com.example.demo.exception;

public class NoPersonFoundException extends RuntimeException{

    public NoPersonFoundException(String format) {
        super(format);
    }
}
