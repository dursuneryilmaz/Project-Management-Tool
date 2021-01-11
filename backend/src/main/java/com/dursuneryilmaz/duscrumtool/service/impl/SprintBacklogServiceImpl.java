package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.SprintBacklog;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.shared.enums.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.SprintBacklogRepository;
import com.dursuneryilmaz.duscrumtool.service.SprintBacklogService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintBacklogServiceImpl implements SprintBacklogService {
    @Autowired
    SprintBacklogRepository sprintBacklogRepository;
    @Autowired
    Utils utils;

    @Override
    public SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog, Sprint sprint) {
        sprintBacklog.setSprintBacklogId(utils.generatePublicId(32));
        sprintBacklog.setSprint(sprint);
        return sprintBacklogRepository.save(sprintBacklog);
    }

    @Override
    public SprintBacklog getSprintBacklogById(String sprintBacklogId) {
        return checkSprintBacklogExistenceById(sprintBacklogId);
    }

    @Override
    public SprintBacklog getBySprint(Sprint sprint) {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findBySprint(sprint);
        if (sprintBacklog != null) return sprintBacklog;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public SprintBacklog updateSprintBacklogById(String sprintBacklogId, SprintBacklog sprintBacklog) {
        SprintBacklog sprintBacklogToUpdate = checkSprintBacklogExistenceById(sprintBacklogId);
        // check later entity updatable false
        BeanUtils.copyProperties(sprintBacklog, sprintBacklogToUpdate);
        return sprintBacklogRepository.save(sprintBacklogToUpdate);
    }

    @Override
    public Boolean deleteSprintBacklogById(String sprintBacklogId) {
        SprintBacklog sprintBacklog = checkSprintBacklogExistenceById(sprintBacklogId);
        sprintBacklogRepository.delete(sprintBacklog);
        return true;
    }

    @Override
    public SprintBacklog transferTasksToNextSprint(String sprintBacklogId) {
        SprintBacklog currentSprintBacklog = checkSprintBacklogExistenceById(sprintBacklogId);
        List<Sprint> sprintList = currentSprintBacklog.getSprint().getProduct().getSprintList();
        int nextSprintIndex = sprintList.indexOf(currentSprintBacklog.getSprint()) + 1;
        Sprint nextSprint = sprintList.get(nextSprintIndex);
        SprintBacklog nextSprintBacklog = getBySprint(nextSprint);

        for (Task t : currentSprintBacklog.getTaskList()) {
            if (!(t.getStatus().equals("CLOSED"))) {
                nextSprintBacklog.getTaskList().add(t);
                currentSprintBacklog.getTaskList().remove(t);
            }
        }
        sprintBacklogRepository.save(currentSprintBacklog);
        return sprintBacklogRepository.save(nextSprintBacklog);
    }


    private SprintBacklog checkSprintBacklogExistenceById(String sprintBacklogId) {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findBySprintBacklogId(sprintBacklogId);
        if (sprintBacklog == null)
            throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return sprintBacklog;
    }
}
