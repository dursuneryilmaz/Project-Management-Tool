package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(String publicId);

    List<Product> findByScrumManagerListContainsOrProductDeveloperListContainsOrStakeHolderListContains(
            User userManager,User userDev, User userSteakHolder);
}
