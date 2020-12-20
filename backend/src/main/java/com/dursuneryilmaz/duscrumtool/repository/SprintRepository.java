package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    Sprint findByProduct(Product product);

    Sprint findBySprintId(String sprintId);
}
