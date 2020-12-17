package com.dursuneryilmaz.dupmtool.web;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.service.ProjectService;
import com.dursuneryilmaz.dupmtool.service.RequestValidationService;
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
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        project.setProjectCode(project.getProjectCode().toUpperCase());
        return new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }
}
