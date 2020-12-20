package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;

public interface SprintService {
    Sprint getByProduct(Product product);

    Sprint getBySprintId(String sprintId);
}
