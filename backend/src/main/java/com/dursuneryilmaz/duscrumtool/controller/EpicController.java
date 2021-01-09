package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.domain.User;
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
@RequestMapping("/api/v1/epics")
public class EpicController {
    @Autowired
    EpicService epicService;
    @Autowired
    ThemeService themeService;
    @Autowired
    StoryService storyService;
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;


    // create epic
    @PostMapping(path = "/{themeId}")
    public ResponseEntity<?> createEpic(@PathVariable String themeId, @Valid @RequestBody Epic epic,
                                        BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Theme theme = themeService.getThemeById(themeId);
        User user = userService.getUserByEmail(principal.getName());
        boolean contains = theme.getProduct().getScrumManagerList().contains(user);
        if (contains) return new ResponseEntity<Epic>(epicService.createEpic(epic, theme), HttpStatus.CREATED);

        return requestValidationService.getAccessDeniedResponseEntity();
    }

    // get epic's stories
    @GetMapping(path = "/{epicId}/stories")
    public ResponseEntity<?> getEpicStories(@PathVariable String epicId, Principal principal) {
        Epic epic = epicService.getEpicById(epicId);
        User user = userService.getUserByEmail(principal.getName());
        boolean managerContains = epic.getTheme().getProduct().getScrumManagerList().contains(user);
        boolean stakeHolderContains = epic.getTheme().getProduct().getStakeHolderList().contains(user);
        if (managerContains | stakeHolderContains)
            return new ResponseEntity<List<Story>>(storyService.getAllByEpic(epic), HttpStatus.OK);
        return requestValidationService.getAccessDeniedResponseEntity();
    }
}
