package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.model.response.OperationName;
import com.dursuneryilmaz.duscrumtool.model.response.OperationStatus;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ThemeService themeService;
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable String productId) {
        return new ResponseEntity<Product>(productService.findProductById(productId), HttpStatus.OK);
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product product, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Product>(productService.updateProductById(productId, product), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<OperationModel> deleteProductByProductId(@PathVariable String productId) {
        OperationModel operationModel = new OperationModel();
        if (productService.deleteProductById(productId)) {
            operationModel.setOperationName(OperationName.DELETE.name());
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
            return new ResponseEntity<>(operationModel, HttpStatus.OK);
        }
        operationModel.setOperationName(OperationName.DELETE.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<>(operationModel, HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}/themes")
    public ResponseEntity<List<Theme>> getProductThemes(@PathVariable String productId) {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<List<Theme>>(themeService.findAllByProduct(product), HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}/themes/{themeId}")
    public ResponseEntity<Theme> getProductTheme(@PathVariable String productId, @PathVariable String themeId) {
        return new ResponseEntity<Theme>(themeService.findByThemeId(themeId), HttpStatus.OK);
    }
}
