package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.*;

import java.util.List;

public interface TaskService {
    Task createTaskToStory(Task task, Story story);

    Task createTaskToProductBacklog(Task task, ProductBacklog productBacklog);

    Task getTaskById(String taskId);

    List<Task> getAllByStory(Story story);

    List<Task> getAllByProductBacklog(ProductBacklog productBacklog);

    List<Task> getAllBySprintBacklog(SprintBacklog sprintBacklog);

    List<Task> getAllByAttendant(User user);

    Task updateTaskById(String taskId, Task task);

    Boolean deleteTaskById(String taskId);

    Task setTaskSprint(Task task, Sprint sprint);

    Task setTaskUser(Task task, User user);

    Task setStatus(Task task, String status);
}
