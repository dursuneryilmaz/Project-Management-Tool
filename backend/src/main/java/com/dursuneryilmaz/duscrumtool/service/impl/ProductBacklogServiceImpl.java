package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.ProductBacklog;
import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.Task;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductBacklogRepository;
import com.dursuneryilmaz.duscrumtool.repository.TaskRepository;
import com.dursuneryilmaz.duscrumtool.service.ProductBacklogService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {
    @Autowired
    ProductBacklogRepository productBacklogRepository;
    @Autowired
    TaskRepository taskRepository;
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
    public ProductBacklog updateProductBacklogById(String productBacklogId, ProductBacklog productBacklog) {
        ProductBacklog productBacklogToUpdate = checkProductBacklogExistenceById(productBacklogId);
        // check later entity updatable false
        BeanUtils.copyProperties(productBacklog, productBacklogToUpdate);
        return productBacklogRepository.save(productBacklogToUpdate);
    }

    @Override
    public Boolean deleteProductBacklogById(String productBacklogId) {
        ProductBacklog productBacklog = checkProductBacklogExistenceById(productBacklogId);
        productBacklogRepository.delete(productBacklog);
        return true;
    }

    @Override
    public ProductBacklog getProductBacklogById(String productBacklogId) {
        return checkProductBacklogExistenceById(productBacklogId);
    }

    private ProductBacklog checkProductBacklogExistenceById(String productBacklogId) {
        ProductBacklog productBacklog = productBacklogRepository.findByProductBacklogId(productBacklogId);
        if (productBacklog == null)
            throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return productBacklog;
    }

    @Override
    public Boolean splitTasksToSprints(ProductBacklog productBacklog) {
        // default task sort by db id
        List<Task> tasks = productBacklog.getTaskList();
        int taskNumber = tasks.size();
        List<Sprint> sprints = productBacklog.getProduct().getSprintList();
        int sprintNumber = sprints.size();
        int taskPerSprint = (int) Math.ceil((double) taskNumber / sprintNumber);
        System.out.println(taskPerSprint);
        // there is an IndexOutOfException when 6 task and 4 sprint exist and similar situations
        List<List<Task>> tasksChunks = Lists.partition(tasks, taskPerSprint);

        for (int i = 0; i < sprintNumber; i++) {
            Sprint sprint = sprints.get(i);
            List<Task> sprintTasks = tasksChunks.get(i);
            for (Task task : sprintTasks) {
                task.setSprintBacklog(sprint.getSprintBacklog());
                taskRepository.save(task);
            }
        }
        return true;
    }
}
