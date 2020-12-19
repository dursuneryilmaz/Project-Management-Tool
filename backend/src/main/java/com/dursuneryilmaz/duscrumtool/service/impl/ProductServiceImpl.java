package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.exception.ProjectCodeException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductRepository;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Utils utils;

    @Override
    public Product createProduct(Product product) {
        product.setProductId(utils.generatePublicId(32));
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(String productId) {
        Product product = productRepository.findByProductId(productId);
        if (product != null) return product;
        throw new ProjectCodeException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }

    @Override
    public Iterable<Product> findAllProducts() {
        Iterable<Product> projects = productRepository.findAll();
        if (projects != null) return projects;
        throw new ProjectCodeException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public Product updateProductById(String productId, Product product) {
        Product productToUpdate = productRepository.findByProductId(productId);
        if (productToUpdate == null)
            throw new ProjectCodeException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        // check later entity updatable false
        BeanUtils.copyProperties(product, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    @Override
    public Boolean deleteProductById(String productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) throw new ProjectCodeException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        productRepository.delete(product);
        return true;
    }
}
