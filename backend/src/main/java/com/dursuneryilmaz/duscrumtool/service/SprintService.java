package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint, Product product);

    Sprint getSprintById(String sprintId);

    List<Sprint> getAllByProduct(Product product);

    Sprint updateSprintById(String sprintId, Sprint sprint);

    Boolean deleteSprintById(String sprintId);
}
