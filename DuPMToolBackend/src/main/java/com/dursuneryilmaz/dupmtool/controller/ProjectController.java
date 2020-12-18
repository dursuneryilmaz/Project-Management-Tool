package com.dursuneryilmaz.dupmtool.controller;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.service.ProjectService;
import com.dursuneryilmaz.dupmtool.service.RequestValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        project.setProjectCode(project.getProjectCode().toUpperCase());
        return new ResponseEntity<Project>(projectService.createProject(project), HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable int projectId) {
        return new ResponseEntity<>(projectService.findProjectById(projectId), HttpStatus.OK);
    }
}
