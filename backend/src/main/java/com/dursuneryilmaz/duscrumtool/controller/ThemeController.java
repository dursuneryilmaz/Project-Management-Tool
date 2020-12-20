package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/themes")
public class ThemeController {
    @Autowired
    ThemeService themeService;
    @Autowired
    ProductService productService;
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createTheme(@PathVariable String productId, @Valid @RequestBody Theme theme, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Product product = productService.getProductById(productId);
        return new ResponseEntity<Theme>(themeService.createTheme(theme, product), HttpStatus.CREATED);
    }
}
