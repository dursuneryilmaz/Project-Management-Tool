package com.dursuneryilmaz.dupmtool.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ValidationService {
    ResponseEntity<?> mapValidationErrors(BindingResult bindingResulte);
}
