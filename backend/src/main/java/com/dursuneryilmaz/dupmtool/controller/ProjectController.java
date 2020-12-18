package com.dursuneryilmaz.dupmtool.controller;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.model.response.OperationModel;
import com.dursuneryilmaz.dupmtool.model.response.OperationName;
import com.dursuneryilmaz.dupmtool.model.response.OperationStatus;
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
        //project.setProjectCode(project.getProjectCode().toUpperCase());
        return new ResponseEntity<Project>(projectService.createProject(project), HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @GetMapping("/{projectCode}")
    public ResponseEntity<Project> getProjectByProjectCode(@PathVariable String projectCode) {
        return new ResponseEntity<Project>(projectService.findProjectByProjectCode(projectCode), HttpStatus.OK);
    }

    @PutMapping("/{projectCode}")
    public ResponseEntity<?> updateProject(@PathVariable String projectCode, @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Project>(projectService.updateProjectByProjectCode(projectCode, project), HttpStatus.OK);
    }

    @DeleteMapping("/{projectCode}")
    public ResponseEntity<OperationModel> deleteProjectById(@PathVariable String projectCode) {
        OperationModel operationModel = new OperationModel();
        if (projectService.deleteProjectByProjectCode(projectCode)) {
            operationModel.setOperationName(OperationName.DELETE.name());
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
            return new ResponseEntity<>(operationModel, HttpStatus.OK);
        }
        operationModel.setOperationName(OperationName.DELETE.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());
        return new ResponseEntity<>(operationModel, HttpStatus.OK);
    }
}
