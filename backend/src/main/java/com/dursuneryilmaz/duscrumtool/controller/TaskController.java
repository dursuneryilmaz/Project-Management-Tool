package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.*;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.shared.enums.OperationName;
import com.dursuneryilmaz.duscrumtool.shared.enums.OperationStatus;
import com.dursuneryilmaz.duscrumtool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    ProductBacklogService productBacklogService;
    @Autowired
    SprintService sprintService;
    @Autowired
    StoryService storyService;
    @Autowired
    SprintBacklogService sprintBacklogService;
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;

    // create task for story
    @PostMapping(path = "/{storyId}/story")
    public ResponseEntity<?> createTaskWithStory(@PathVariable String storyId, @Valid @RequestBody Task task,
                                                 Principal principal, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;

        Story story = storyService.getStoryById(storyId);
        // -> ? set every related objet a product id ?
        boolean contains = story.getEpic().getTheme().getProduct().getScrumManagerList().contains(
                userService.getUserByEmail(principal.getName()));
        if (contains) return new ResponseEntity<Task>(taskService.createTaskToStory(task, story), HttpStatus.CREATED);
        return requestValidationService.getAccessDeniedResponseEntity();
    }


    // create task for product backlog
    @PostMapping(path = "/{productBacklogId}/product-backlog")
    public ResponseEntity<?> createTaskWithProductBacklog(@PathVariable String productBacklogId, @Valid @RequestBody Task task,
                                                          BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        ProductBacklog productBacklog = productBacklogService.getProductBacklogById(productBacklogId);
        boolean contains = productBacklog.getProduct().getScrumManagerList().contains(userService.getUserByEmail(principal.getName()));
        if (contains)
            return new ResponseEntity<Task>(taskService.createTaskToProductBacklog(task, productBacklog), HttpStatus.CREATED);
        return requestValidationService.getAccessDeniedResponseEntity();
    }

    // get task by id
    @GetMapping(path = "/{taskId}")
    public ResponseEntity<Task> getProductByProductId(@PathVariable String taskId) {
        return new ResponseEntity<Task>(taskService.getTaskById(taskId), HttpStatus.OK);
    }

    // update task
    @PutMapping(path = "/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable String taskId, @Valid @RequestBody Task task, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Task>(taskService.updateTaskById(taskId, task), HttpStatus.OK);
    }

    // delete task
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<OperationModel> deleteTaskByTaskId(@PathVariable String taskId) {
        OperationModel operationModel = new OperationModel();
        if (taskService.deleteTaskById(taskId)) {
            operationModel.setOperationName(OperationName.DELETE.name());
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
            return new ResponseEntity<>(operationModel, HttpStatus.OK);
        }
        operationModel.setOperationName(OperationName.DELETE.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<>(operationModel, HttpStatus.OK);
    }

    // add task to sprint
    @PutMapping(path = "/{sprintId}/sprint")
    public ResponseEntity<?> addTaskToSprint(@PathVariable String sprintId, @Valid @RequestBody Task task, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Sprint sprint = sprintService.getSprintById(sprintId);
        return new ResponseEntity<Task>(taskService.setTaskSprint(task, sprint), HttpStatus.OK);
    }
/*
    // add task to user
    @PutMapping(path = "/{userId}/sprint")
    public ResponseEntity<?> addTaskToUser(@PathVariable String userId, @Valid @RequestBody Task task, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        User user = userService.getUserById(userId);
        return new ResponseEntity<Task>(taskService.setTaskUser(task, user), HttpStatus.OK);
    }
*/

    // set task status
}
