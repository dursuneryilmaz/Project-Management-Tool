package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.shared.enums.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.shared.enums.OperationStatus;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class RequestValidationServiceImpl implements RequestValidationService {

    @Override
    public ResponseEntity<?> mapValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<OperationModel> getAccessDeniedResponseEntity() {
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName(ExceptionMessages.ACCESS_DENIED.getExceptionMessage());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<OperationModel>(operationModel, HttpStatus.BAD_REQUEST);
    }
}
