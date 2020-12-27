package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.service.ProductBacklogService;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-backlogs")
public class ProductBacklogController {
    @Autowired
    ProductBacklogService productBacklogService;
    @Autowired
    ProductService productService;
    @Autowired
    TaskService taskService;
    @Autowired
    RequestValidationService requestValidationService;

    // create product backlog
    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createProductBacklog(@PathVariable String productId, @Valid @RequestBody ProductBacklog productBacklog, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Product product = productService.getProductById(productId);
        return new ResponseEntity<ProductBacklog>(productBacklogService.createProductBacklog(productBacklog, product), HttpStatus.CREATED);
    }

    // get product backlog -> this returns task list also
    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductBacklog> getProductBacklog(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<ProductBacklog>(productBacklogService.getByProduct(product), HttpStatus.OK);
    }

    // get product backlog's tasks -> where to use
    @GetMapping(path = "/{productBacklogId/tasks}")
    public ResponseEntity<List<Task>> getProductBacklogTasks(@PathVariable String productBacklogId) {
        ProductBacklog productBacklog = productBacklogService.getProductBacklogById(productBacklogId);
        return new ResponseEntity<List<Task>>(taskService.getAllByProductBacklog(productBacklog), HttpStatus.OK);
    }
}
