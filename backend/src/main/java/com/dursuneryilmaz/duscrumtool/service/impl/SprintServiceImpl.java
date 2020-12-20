package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.SprintRepository;
import com.dursuneryilmaz.duscrumtool.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintServiceImpl implements SprintService {
    @Autowired
    SprintRepository sprintRepository;

    @Override
    public Sprint getByProduct(Product product) {
        Sprint sprint = sprintRepository.findByProduct(product);
        if (sprint != null) return sprint;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public Sprint getBySprintId(String sprintId) {
        Sprint sprint = sprintRepository.findBySprintId(sprintId);
        if (sprint != null) return sprint;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }
}
