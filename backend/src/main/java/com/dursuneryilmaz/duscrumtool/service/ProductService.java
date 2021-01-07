package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.User;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product, User user);

    Product getProductById(String productId, User user);

    List<Product> getAllProducts(User user);

    Product updateProductById(String productId, Product product, User user);

    Boolean deleteProductById(String productId, User user);

    List<User> getProductStakeHolders(Product product);

    List<User> getProductScrumManagers(Product product);

    List<User> getProductDevelopers(Product product);

    List<User> addProductStakeHolder(Product product, User user);

    List<User> addProductScrumManager(Product product, User user);

    List<User> addProductDeveloper(Product product, User user);
}
