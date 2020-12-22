package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
    SprintBacklog findBySprint(Sprint sprint);

    SprintBacklog findBySprintBacklogId(String springBacklogId);
}
