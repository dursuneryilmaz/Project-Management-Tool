package com.dursuneryilmaz.duscrumtool.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface RequestValidationService {
    ResponseEntity<?> mapValidationErrors(BindingResult bindingResulte);
}
