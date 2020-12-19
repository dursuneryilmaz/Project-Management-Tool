package com.dursuneryilmaz.duscrumtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectCodeException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 5874968188971296689L;

    public ProjectCodeException(String message) {
        super(message);
    }
}
