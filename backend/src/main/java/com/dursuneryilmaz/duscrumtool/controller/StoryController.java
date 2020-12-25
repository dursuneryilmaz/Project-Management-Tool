package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.service.EpicService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.StoryService;
import com.dursuneryilmaz.duscrumtool.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    RequestValidationService requestValidationService;

    // create story
    @PostMapping(path = "/{epicId}")
    public ResponseEntity<?> createStory(@PathVariable String epicId, @Valid @RequestBody Story story, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Epic epic = epicService.getEpicById(epicId);
        return new ResponseEntity<Story>(storyService.createStory(story, epic), HttpStatus.CREATED);
    }

    // get story's tasks
    @GetMapping(path = "/{storyId}/tasks")
    public ResponseEntity<List<Task>> getEpicStories(@PathVariable String storyId) {
        Story story = storyService.getStoryById(storyId);
        return new ResponseEntity<List<Task>>(taskService.getAllByStory(story), HttpStatus.OK);
    }
}
