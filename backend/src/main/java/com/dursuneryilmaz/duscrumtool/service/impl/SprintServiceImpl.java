package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.SprintBacklog;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.SprintRepository;
import com.dursuneryilmaz.duscrumtool.service.SprintService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    Utils utils;

    @Override
    public Sprint createSprint(Sprint sprint, Product product) {
        //instantiate and assign sprint backlog before sprint creation
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setSprintBacklogId(utils.generatePublicId(32));
        // any problem with db id of sprint -> ?
        sprintBacklog.setSprint(sprint);

        sprint.setSprintId(utils.generatePublicId(32));
        sprint.setSprintBacklog(sprintBacklog);
        sprint.setProduct(product);
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint getSprintById(String sprintId) {
        return checkSprintExistenceById(sprintId);
    }

    @Override
    public List<Sprint> getAllByProduct(Product product) {
        List<Sprint> sprintList = sprintRepository.findAllByProduct(product);
        if (sprintList.size() != 0) return sprintList;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public Sprint updateSprintById(String sprintId, Sprint sprint) {
        Sprint sprintToUpdate = checkSprintExistenceById(sprintId);
        // check later entity updatable false
        BeanUtils.copyProperties(sprint, sprintToUpdate);
        return sprintRepository.save(sprintToUpdate);
    }

    @Override
    public Boolean deleteSprintById(String sprintId) {
        Sprint sprint = checkSprintExistenceById(sprintId);
        sprintRepository.delete(sprint);
        return true;
    }

    private Sprint checkSprintExistenceById(String sprintId) {
        Sprint sprint = sprintRepository.findBySprintId(sprintId);
        if (sprint == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return sprint;
    }
}
