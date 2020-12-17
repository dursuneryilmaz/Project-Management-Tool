package com.dursuneryilmaz.dupmtool.service.impl;

import com.dursuneryilmaz.dupmtool.domain.Project;
import com.dursuneryilmaz.dupmtool.repository.ProjectRepository;
import com.dursuneryilmaz.dupmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdate(Project project) {
        // logic
        return projectRepository.save(project);
    }
}
