package com.api.expense.exception;


import com.api.expense.exception.custom.IncorrectDateException;
import com.api.expense.exception.custom.ResourceNotFoundException;
import com.api.expense.exception.custom.UniqueKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        return handleException("Resource not found", request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {

        return handleException(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectDateException.class)
    public ResponseEntity<ErrorMessage> incorrectDateException(Exception ex, WebRequest request) {

        return handleException(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> methodNotFound(Exception ex, WebRequest request) {

        return handleException("This resource is not found, please check url", request, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UniqueKeyException.class)
    public ResponseEntity<ErrorMessage> uniqueKeyException(Exception ex, WebRequest request) {

        return handleException(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<ErrorMessage> handleException(String errorMessage, WebRequest request, HttpStatus httpStatus) {
        ErrorMessage message = new ErrorMessage(
                httpStatus.value(),
                new Date(),
                errorMessage,
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatusCode.valueOf(httpStatus.value()));
    }

}
