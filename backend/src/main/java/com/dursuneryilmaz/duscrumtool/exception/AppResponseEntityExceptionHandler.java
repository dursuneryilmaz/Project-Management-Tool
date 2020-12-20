package com.dursuneryilmaz.duscrumtool.exception;

import com.dursuneryilmaz.duscrumtool.model.response.ProductIdExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ProductIdException.class})
    public ResponseEntity<Object> handleUserServiceException(ProductIdException ex, WebRequest request) {
        ProductIdExceptionModel productIdExceptionModel = new ProductIdExceptionModel(ex.getMessage());
        return new ResponseEntity(productIdExceptionModel, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
