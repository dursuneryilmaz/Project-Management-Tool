package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.*;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.TaskRepository;
import com.dursuneryilmaz.duscrumtool.service.TaskService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Utils utils;

    @Override
    public Task createTask(Task task, Story story) {
        task.setTaskId(utils.generatePublicId(32));
        task.setStory(story);
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(String taskId) {
        return checkTaskExistenceById(taskId);
    }

    @Override
    public List<Task> getAllByStory(Story story) {
        List<Task> taskList = taskRepository.findAllByStory(story);
        if (taskList.size() != 0) return taskList;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public List<Task> getAllByProductBacklog(ProductBacklog productBacklog) {
        List<Task> taskList = taskRepository.findAllByProductBacklog(productBacklog);
        if (taskList.size() != 0) return taskList;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public List<Task> getAllBySprintBacklog(SprintBacklog sprintBacklog) {
        List<Task> taskList = taskRepository.findAllBySprintBacklog(sprintBacklog);
        if (taskList.size() != 0) return taskList;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public List<Task> getAllByAttendant(User user) {
        List<Task> taskList = taskRepository.findAllByAttendant(user);
        if (taskList.size() != 0) return taskList;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public Task updateTaskById(String taskId, Task task) {
        Task taskToUpdate = checkTaskExistenceById(taskId);
        BeanUtils.copyProperties(task, taskToUpdate);
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Boolean deleteTaskById(String taskId) {
        Task task = checkTaskExistenceById(taskId);
        taskRepository.delete(task);
        return true;
    }

    @Override
    public Task setTaskSprint(Task task, Sprint sprint) {
        task.setSprintBacklog(sprint.getSprintBacklog());
        return task;
    }

    @Override
    public Task setTaskUser(Task task, User user) {
        task.setAttendant(user);
        return taskRepository.save(task);
    }

    @Override
    public Task setStatus(Task task, String status) {
        task.setStatus(status);
        return taskRepository.save(task);
    }

    private Task checkTaskExistenceById(String taskId) {
        Task task = taskRepository.findByTaskId(taskId);
        if (task != null) return task;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }
}
