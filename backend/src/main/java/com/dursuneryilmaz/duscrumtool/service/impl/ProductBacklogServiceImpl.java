package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductBacklogRepository;
import com.dursuneryilmaz.duscrumtool.service.ProductBacklogService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {
    @Autowired
    ProductBacklogRepository productBacklogRepository;
    @Autowired
    Utils utils;

    @Override
    public ProductBacklog createProductBacklog(ProductBacklog productBacklog, Product product) {
        productBacklog.setProductBacklogId(utils.generatePublicId(32));
        productBacklog.setProduct(product);
        return productBacklogRepository.save(productBacklog);
    }

    @Override
    public ProductBacklog getByProduct(Product product) {
        ProductBacklog productBacklog = productBacklogRepository.findByProduct(product);
        if (productBacklog != null) return productBacklog;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public ProductBacklog getByProductBacklogId(String productBacklogId) {
        ProductBacklog productBacklog = productBacklogRepository.findByProductBacklogId(productBacklogId);
        if (productBacklog != null) return productBacklog;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }
}
