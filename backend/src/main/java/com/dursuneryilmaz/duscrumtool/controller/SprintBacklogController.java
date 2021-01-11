package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.SprintBacklog;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.SprintBacklogService;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/sprint-backlogs")
public class SprintBacklogController {
    @Autowired
    SprintBacklogService sprintBacklogService;
    @Autowired
    RequestValidationService requestValidationService;
    @Autowired
    UserService userService;

    @GetMapping(path = "/{sprintBacklogId}/transfer-tasks")
    public ResponseEntity<?> transferTasksToNextSprint(@PathVariable String sprintBacklogId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        SprintBacklog sprintBacklog = sprintBacklogService.getSprintBacklogById(sprintBacklogId);
        boolean contains = sprintBacklog.getSprint().getProduct().getScrumManagerList().contains(user);
        if (contains) {
            return new ResponseEntity<SprintBacklog>(sprintBacklogService.transferTasksToNextSprint(sprintBacklogId),
                    HttpStatus.OK);
        }
        return requestValidationService.getAccessDeniedResponseEntity();
    }
}
