package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductRepository;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Product getProductById(String productId) {
        return checkProductExistenceById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> projects = productRepository.findAll();
        if (projects.size() > 0) return projects;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public Product updateProductById(String productId, Product product) {
        Product productToUpdate = checkProductExistenceById(productId);
        BeanUtils.copyProperties(product, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    @Override
    public Boolean deleteProductById(String productId) {
        Product product = checkProductExistenceById(productId);
        productRepository.delete(product);
        return true;
    }

    private Product checkProductExistenceById(String productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return product;
    }
}
