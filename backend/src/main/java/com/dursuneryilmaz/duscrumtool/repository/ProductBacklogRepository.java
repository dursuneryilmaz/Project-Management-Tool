package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
    ProductBacklog findByProduct(Product product);

    ProductBacklog findByProductBacklogId(String productBacklogId);
}
