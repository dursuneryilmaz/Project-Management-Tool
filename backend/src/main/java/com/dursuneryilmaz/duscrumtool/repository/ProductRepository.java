package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductId(String publicId);

    @Override
    Iterable<Product> findAll();
}
