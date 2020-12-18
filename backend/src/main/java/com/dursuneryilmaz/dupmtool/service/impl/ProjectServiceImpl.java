package com.dursuneryilmaz.dupmtool.service.impl;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.exception.ProjectCodeException;
import com.dursuneryilmaz.dupmtool.repository.ProjectRepository;
import com.dursuneryilmaz.dupmtool.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        if (projectRepository.findByProjectCode(project.getProjectCode()) != null)
            throw new ProjectCodeException("Project Code Already Exist");
        return projectRepository.save(project);
    }

    @Override
    public Project findProjectByProjectCode(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        if (project != null) return project;
        throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
    }

    @Override
    public Project findProjectById(int projectId) {
        Project project = projectRepository.findById(projectId);
        if (project != null) return project;
        throw new ProjectCodeException("Project Does Not Exist With Provided project Id");
    }

    @Override
    public Iterable<Project> findAllProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        if (projects != null) return projects;
        throw new ProjectCodeException("There is no Project Yet");
    }

    @Override
    public Project updateProjectByProjectCode(String projectCode, Project project) {
        Project projectToUpdate = projectRepository.findByProjectCode(projectCode);
        if (projectToUpdate == null) throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
        // check later entity updatable false
        BeanUtils.copyProperties(project, projectToUpdate);
        return projectRepository.save(projectToUpdate);
    }

    @Override
    public Boolean deleteProjectByProjectCode(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        if (project == null) throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
        projectRepository.delete(project);
        return true;
    }
}
