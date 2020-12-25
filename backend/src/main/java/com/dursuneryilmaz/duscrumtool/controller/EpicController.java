package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.service.EpicService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.StoryService;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/epics")
public class EpicController {
    @Autowired
    EpicService epicService;
    @Autowired
    ThemeService themeService;
    @Autowired
    StoryService storyService;
    @Autowired
    RequestValidationService requestValidationService;

    // create epic
    @PostMapping(path = "/{themeId}")
    public ResponseEntity<?> createEpic(@PathVariable String themeId, @Valid @RequestBody Epic epic, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Theme theme = themeService.getThemeById(themeId);
        return new ResponseEntity<Epic>(epicService.createEpic(epic, theme), HttpStatus.CREATED);
    }

    // get epic's stories
    @GetMapping(path = "/{epicId}/stories")
    public ResponseEntity<List<Story>> getEpicStories(@PathVariable String epicId) {
        Epic epic = epicService.getEpicById(epicId);
        return new ResponseEntity<List<Story>>(storyService.getAllByEpic(epic), HttpStatus.OK);
    }
}
