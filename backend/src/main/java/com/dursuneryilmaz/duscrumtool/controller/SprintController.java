package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.SprintService;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/sprints")
public class SprintController {
    @Autowired
    SprintService sprintService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;

    // create sprint with sprint backlog
    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createSprint(@PathVariable String productId, @RequestBody Sprint sprint,
                                          BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<Sprint>(sprintService.createSprint(sprint, product), HttpStatus.CREATED);
    }

    // get all sprints of a product
    @GetMapping(path = "/{productId}")
    public ResponseEntity<List<Sprint>> getSprints(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<List<Sprint>>(sprintService.getAllByProduct(product), HttpStatus.OK);
    }
}
