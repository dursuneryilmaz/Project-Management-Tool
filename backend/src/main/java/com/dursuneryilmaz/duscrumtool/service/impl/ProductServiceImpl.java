package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.exception.ProjectCodeException;
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
        product.setPublicId(utils.generatePublicId(32));
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(String productId) {
        Product product = productRepository.findByPublicId(productId);
        if (product != null) return product;
        throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
    }

    @Override
    public Iterable<Product> findAllProducts() {
        Iterable<Product> projects = productRepository.findAll();
        if (projects != null) return projects;
        throw new ProjectCodeException("There is no Project Yet");
    }

    @Override
    public Product updateProductById(String productId, Product product) {
        Product productToUpdate = productRepository.findByPublicId(productId);
        if (productToUpdate == null)
            throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
        // check later entity updatable false
        BeanUtils.copyProperties(product, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    @Override
    public Boolean deleteProductById(String productId) {
        Product product = productRepository.findByPublicId(productId);
        if (product == null) throw new ProjectCodeException("Project Does Not Exist With Provided Project Code");
        productRepository.delete(product);
        return true;
    }
}
