package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface RequestValidationService {
    ResponseEntity<?> mapValidationErrors(BindingResult bindingResulte);

    ResponseEntity<OperationModel> getAccessDeniedResponseEntity();
}
