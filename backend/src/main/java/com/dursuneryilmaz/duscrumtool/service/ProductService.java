package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(String productId);

    List<Product> getAllProducts();

    Product updateProductById(String productId, Product product);

    Boolean deleteProductById(String productId);
}
