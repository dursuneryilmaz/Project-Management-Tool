package com.dursuneryilmaz.dupmtool.repository;

import com.dursuneryilmaz.dupmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByProjectCode(String projectCode);
    Project findById(int id);

    @Override
    Iterable<Project> findAll();
}
