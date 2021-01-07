package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductRepository;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
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
    public Product createProduct(Product product, User user) {
        // instantiate and assign product backlog before product creation
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setProductBacklogId(utils.generatePublicId(32));
        // any problem with db id of product
        productBacklog.setProduct(product);

        product.setProductId(utils.generatePublicId(32));
        product.getScrumManagerList().add(user);
        product.setProductBacklog(productBacklog);

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String productId, User user) {
        return checkProductExistenceById(productId, user);
    }

    @Override
    public List<Product> getAllProducts(User user) {
        List<Product> projects =
                productRepository.findByScrumManagerListContainsOrProductDeveloperListContainsOrStakeHolderListContains(
                        user, user, user);

        if (projects.size() > 0) return projects;
        throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
    }

    @Override
    public Product updateProductById(String productId, Product product, User user) {
        Product productToUpdate = checkProductExistenceById(productId, user);
        // update selected properties which are required
        productToUpdate.setProjectName(product.getProjectName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCost(product.getCost());
        return productRepository.save(productToUpdate);
    }

    // there will be voting among scrum managers
    @Override
    public Boolean deleteProductById(String productId, User user) {
        Product product = checkProductExistenceById(productId, user);
        productRepository.delete(product);
        return true;
    }

    private Product checkProductExistenceById(String productId, User user) {
        Product product = productRepository.findByProductIdAndScrumManagerListContains(productId, user);
        if (product == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return product;
    }

    @Override
    public List<User> getProductStakeHolders(Product product) {
        if (product.getStakeHolderList().size() != 0) return product.getStakeHolderList();
        throw new ProductIdException(ExceptionMessages.NO_STAKE_HOLDER.getExceptionMessage());
    }

    @Override
    public List<User> getProductScrumManagers(Product product) {
        if (product.getScrumManagerList().size() != 0) return product.getScrumManagerList();
        throw new ProductIdException(ExceptionMessages.NO_SCRUM_MANAGER.getExceptionMessage());
    }

    @Override
    public List<User> getProductDevelopers(Product product) {
        if (product.getProductDeveloperList().size() != 0) return product.getProductDeveloperList();
        throw new ProductIdException(ExceptionMessages.NO_WORKING_DEVELOPER.getExceptionMessage());
    }

    @Override
    public List<User> addProductStakeHolder(Product product, User user) {
        product.getStakeHolderList().add(user);
        return productRepository.save(product).getStakeHolderList();

    }

    @Override
    public List<User> addProductScrumManager(Product product, User user) {
        product.getScrumManagerList().add(user);
        return productRepository.save(product).getScrumManagerList();
    }

    @Override
    public List<User> addProductDeveloper(Product product, User user) {
        product.getProductDeveloperList().add(user);
        return productRepository.save(product).getProductDeveloperList();

    }
}
