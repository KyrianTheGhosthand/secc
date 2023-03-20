package com.example.demosale.exception;

public record ErrorResponse(int status, String message) {
    public ErrorResponse(ApiException ex){
        this(ex.getStatus(), ex.getMessage());
    }
}
