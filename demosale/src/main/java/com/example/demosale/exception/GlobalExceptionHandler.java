package com.example.demosale.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(ex));
    }

    @ExceptionHandler({
            BindException.class,
            ConstraintViolationException.class,
            MaxUploadSizeExceededException.class,
            MissingServletRequestPartException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {
        final var defaultMessage = "Invalid Data";
        var message = defaultMessage;
        if (ex instanceof BindException castedEx) {
            message = Util.getBindExceptionMessage(castedEx, defaultMessage);
        } else if (ex instanceof ConstraintViolationException exception) {
            message = Util.getConstraintViolationExceptionMessage(exception, defaultMessage);
        }
        return ResponseEntity.status(400).body(new ErrorResponse(400, message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex) {
        return ResponseEntity.status(403).body(new ErrorResponse(403, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnwantedException(Exception ex) {
        return ResponseEntity.status(500).body(new ErrorResponse(500, ex.getMessage()));
    }
}
