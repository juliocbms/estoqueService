package com.microservice.estoque.Service.exception;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(Long msg) {
        super(String.valueOf(msg));
    }
}
