package com.dursuneryilmaz.dupmtool.web;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Invalid Object", HttpStatus.BAD_REQUEST);
        }
        project = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
