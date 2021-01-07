package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.*;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.model.response.OperationName;
import com.dursuneryilmaz.duscrumtool.model.response.OperationStatus;
import com.dursuneryilmaz.duscrumtool.service.*;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ThemeService themeService;
    @Autowired
    ProductBacklogService productBacklogService;
    @Autowired
    SprintService sprintService;
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;
    @Autowired
    Utils utils;

    // create product
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult,
                                           Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User user = userService.getUserByEmail(principal.getName());
        return new ResponseEntity<Product>(productService.createProduct(product, user), HttpStatus.CREATED);
    }

    // get all products
    @GetMapping
    public List<Product> getAllProducts(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return productService.getAllProducts(user);
    }

    // get product
    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return new ResponseEntity<Product>(productService.getProductById(productId, user), HttpStatus.OK);
    }

    // update product
    @PutMapping(path = "/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId, @Valid @RequestBody Product product,
                                           BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User user = userService.getUserByEmail(principal.getName());
        return new ResponseEntity<Product>(productService.updateProductById(productId, product, user), HttpStatus.OK);
    }

    // delete product
    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<OperationModel> deleteProductByProductId(@PathVariable String productId, Principal principal) {
        OperationModel operationModel = new OperationModel();
        User user = userService.getUserByEmail(principal.getName());
        if (productService.deleteProductById(productId, user)) {
            operationModel.setOperationName(OperationName.DELETE.name());
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
            return new ResponseEntity<>(operationModel, HttpStatus.OK);
        }
        operationModel.setOperationName(OperationName.DELETE.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<>(operationModel, HttpStatus.OK);
    }

    // get product themes
    @GetMapping(path = "/{productId}/themes")
    public ResponseEntity<List<Theme>> getProductThemes(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<Theme>>(themeService.getAllByProduct(product), HttpStatus.OK);
    }

    // get product backlog
    @GetMapping(path = "/{productId}/backlog")
    public ResponseEntity<ProductBacklog> getProductBacklog(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<ProductBacklog>(productBacklogService.getByProduct(product), HttpStatus.OK);
    }

    // get product sprints
    @GetMapping(path = "/{productId}/sprints")
    public ResponseEntity<List<Sprint>> getProductSprints(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<Sprint>>(sprintService.getAllByProduct(product), HttpStatus.OK);
    }

    // get stake holders
    @GetMapping(path = "/{productId}/stake-holders")
    public ResponseEntity<List<User>> getProductStakeHolders(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<User>>(productService.getProductStakeHolders(product), HttpStatus.OK);
    }

    // add stake holder
    @PatchMapping(path = "/{productId}/stake-holders")
    public ResponseEntity<?> addProductStakeHolder(@PathVariable String productId, @Valid @RequestBody User user,
                                                   BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User loggedUser = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, loggedUser);
        return new ResponseEntity<List<User>>(productService.addProductStakeHolder(product, user), HttpStatus.OK);
    }

    // get scrum managers
    @GetMapping(path = "/{productId}/scrum-managers")
    public ResponseEntity<List<User>> getProductScrumManagers(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<User>>(productService.getProductScrumManagers(product), HttpStatus.OK);
    }

    // add scrum manager
    @PatchMapping(path = "/{productId}/scrum-managers")
    public ResponseEntity<?> addProductScrumManager(@PathVariable String productId, @Valid @RequestBody User user,
                                                    BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User loggedUser = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, loggedUser);
        return new ResponseEntity<List<User>>(productService.addProductScrumManager(product, user), HttpStatus.OK);
    }

    // get scrum devs
    @GetMapping(path = "/{productId}/developers")
    public ResponseEntity<List<User>> getProductDevelopers(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<User>>(productService.getProductDevelopers(product), HttpStatus.OK);
    }

    // add scrum dev
    @PatchMapping(path = "/{productId}/developers")
    public ResponseEntity<?> addProductDeveloper(@PathVariable String productId, @RequestBody User user,
                                                 BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User loggedUser = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, loggedUser);
        return new ResponseEntity<List<User>>(productService.addProductDeveloper(product, user), HttpStatus.OK);
    }
}
