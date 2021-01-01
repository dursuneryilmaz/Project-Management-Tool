package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;

public interface ProductBacklogService {
    ProductBacklog createProductBacklog(ProductBacklog productBacklog, Product product);

    ProductBacklog getProductBacklogById(String productBacklogId);

    ProductBacklog getByProduct(Product product);

    ProductBacklog updateProductBacklogById(String backlogId, ProductBacklog productBacklog);

    Boolean deleteProductBacklogById(String productBacklogId);

    Boolean splitTasksToSprints(ProductBacklog productBacklog);
}
