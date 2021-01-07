package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.model.response.OperationName;
import com.dursuneryilmaz.duscrumtool.model.response.OperationStatus;
import com.dursuneryilmaz.duscrumtool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;

    // create product backlog -> no need ?
    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createProductBacklog(@PathVariable String productId,
                                                  @Valid @RequestBody ProductBacklog productBacklog,
                                                  BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<ProductBacklog>(productBacklogService.createProductBacklog(productBacklog, product), HttpStatus.CREATED);
    }

    // get product backlog -> this returns task list also
    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductBacklog> getProductBacklog(@PathVariable String productId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(productId, user);
        return new ResponseEntity<ProductBacklog>(productBacklogService.getByProduct(product), HttpStatus.OK);
    }

    // get product backlog's tasks -> where to use
    @GetMapping(path = "/{productBacklogId/tasks}")
    public ResponseEntity<List<Task>> getProductBacklogTasks(@PathVariable String productBacklogId) {
        ProductBacklog productBacklog = productBacklogService.getProductBacklogById(productBacklogId);
        return new ResponseEntity<List<Task>>(taskService.getAllByProductBacklog(productBacklog), HttpStatus.OK);
    }

    // split tasks to sprints
    @GetMapping(path = "/{productBacklogId}/split-tasks-to-sprints")
    public ResponseEntity<OperationModel> splitTaskToSprints(@PathVariable String productBacklogId) {
        OperationModel operationModel = new OperationModel();
        if (productBacklogService.splitTasksToSprints(productBacklogService.getProductBacklogById(productBacklogId))) {
            operationModel.setOperationName(OperationName.SPLIT_TASKS.name());
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
            return new ResponseEntity<>(operationModel, HttpStatus.OK);
        }
        operationModel.setOperationName(OperationName.SPLIT_TASKS.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<>(operationModel, HttpStatus.OK);
    }
}
