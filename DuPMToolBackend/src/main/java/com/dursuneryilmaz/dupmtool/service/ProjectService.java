package com.dursuneryilmaz.dupmtool.service;

import com.dursuneryilmaz.dupmtool.domain.Project;

public interface ProjectService {
    Project saveOrUpdate(Project project);
}
