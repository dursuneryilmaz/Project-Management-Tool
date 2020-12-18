package com.dursuneryilmaz.dupmtool.service;

import com.dursuneryilmaz.dupmtool.domain.Project;

public interface ProjectService {
    Project createProject(Project project);
    Project findProjectByProjectCode(String projectCode);
    Project findProjectById(int projectId);
    Iterable<Project> findAllProjects();
    Project updateProjectByProjectCode(String projectCode, Project project);
    Boolean deleteProjectByProjectCode(String projectCode);

}
