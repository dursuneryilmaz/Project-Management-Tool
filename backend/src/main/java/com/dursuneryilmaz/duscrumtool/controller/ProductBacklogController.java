package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.service.ProductBacklogService;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-backlogs")
public class ProductBacklogController {
    @Autowired
    ProductBacklogService productBacklogService;
    @Autowired
    ProductService productService;
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createTheme(@PathVariable String productId, @Valid @RequestBody ProductBacklog productBacklog, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Product product = productService.getProductById(productId);
        return new ResponseEntity<ProductBacklog>(productBacklogService.createProductBacklog(productBacklog, product), HttpStatus.CREATED);
    }
}
