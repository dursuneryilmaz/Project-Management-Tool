package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
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
@RequestMapping("/api/v1/stories")
public class StoryController {
    @Autowired
    StoryService storyService;
    @Autowired
    EpicService epicService;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;


    // create story
    @PostMapping(path = "/{epicId}")
    public ResponseEntity<?> createStory(@PathVariable String epicId, @Valid @RequestBody Story story, Principal principal,
                                         BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Epic epic = epicService.getEpicById(epicId);
        boolean contains = epic.getTheme().getProduct().getScrumManagerList().contains(
                userService.getUserByEmail(principal.getName()));
        if (contains) return new ResponseEntity<Story>(storyService.createStory(story, epic), HttpStatus.CREATED);
        return requestValidationService.getAccessDeniedResponseEntity();
    }

    // get story's tasks
    @GetMapping(path = "/{storyId}/tasks")
    public ResponseEntity<?> getEpicStories(@PathVariable String storyId, Principal principal) {
        Story story = storyService.getStoryById(storyId);
        User user = userService.getUserByEmail(principal.getName());

        boolean managerContains = story.getEpic().getTheme().getProduct().getScrumManagerList().contains(user);
        boolean stakeHolderContains = story.getEpic().getTheme().getProduct().getStakeHolderList().contains(user);
        boolean developerContains = story.getEpic().getTheme().getProduct().getProductDeveloperList().contains(user);
        if (managerContains | stakeHolderContains | developerContains)
            return new ResponseEntity<List<Task>>(taskService.getAllByStory(story), HttpStatus.OK);
        return requestValidationService.getAccessDeniedResponseEntity();
    }
}
