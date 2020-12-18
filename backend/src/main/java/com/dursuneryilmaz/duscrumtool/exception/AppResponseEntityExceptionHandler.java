package com.dursuneryilmaz.duscrumtool.exception;

import com.dursuneryilmaz.duscrumtool.model.response.ProjectCodeExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ProjectCodeException.class})
    public ResponseEntity<Object> handleUserServiceException(ProjectCodeException ex, WebRequest request) {
        ProjectCodeExceptionModel projectCodeExceptionModel = new ProjectCodeExceptionModel(ex.getMessage());
        return new ResponseEntity(projectCodeExceptionModel, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
