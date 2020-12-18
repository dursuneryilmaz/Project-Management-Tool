package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product findProductById(String productId);

    Iterable<Product> findAllProducts();

    Product updateProductById(String productId, Product product);

    Boolean deleteProductById(String productId);
}
