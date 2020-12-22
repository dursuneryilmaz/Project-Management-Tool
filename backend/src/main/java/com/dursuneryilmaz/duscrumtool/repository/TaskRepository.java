package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskId(String taskId);

    List<Task> findAllByStory(Story story);

    List<Task> findAllByProductBacklog(ProductBacklog productBacklog);

    List<Task> findAllBySprintBacklog(SprintBacklog sprintBacklog);

    List<Task> findAllByAttendant(User user);
}
