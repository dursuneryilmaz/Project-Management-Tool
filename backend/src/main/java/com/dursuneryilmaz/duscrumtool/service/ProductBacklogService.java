package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;

public interface ProductBacklogService {
    ProductBacklog createProductBacklog(ProductBacklog productBacklog, Product product);

    ProductBacklog getByProduct(Product product);

    ProductBacklog getByProductBacklogId(String productBacklogId);
}
