package com.example.demosale.exception;

import org.springframework.validation.BindException;

import javax.validation.ConstraintViolationException;


public class Util {
    public static String getBindExceptionMessage(BindException ex, String defaultMessage) {
        if (!ex.hasErrors() || ex.getAllErrors().isEmpty()) {
            return defaultMessage;
        }
        return ex.getAllErrors().get(0).getDefaultMessage();
    }

    public static String getConstraintViolationExceptionMessage(
            ConstraintViolationException ex,
            String defaultMessage
    ) {
        var firstViolation = ex.getConstraintViolations().stream().findFirst();
        if (firstViolation.isEmpty()) {
            return defaultMessage;
        }
        return firstViolation.get().getMessage();
    }
}
