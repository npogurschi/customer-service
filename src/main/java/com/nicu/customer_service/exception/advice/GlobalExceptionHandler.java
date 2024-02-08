package com.nicu.customer_service.exception.advice;

import com.nicu.customer_service.exception.CustomerNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFound.class)
    String customerNotFoundHandler(CustomerNotFound ex) {
        return ex.getMessage();
    }
}