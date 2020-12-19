package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_backlogs")
public class ProductBacklog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @Size(min = 32, max = 32)
    private String productBacklogId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnore
    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> taskList;

    public ProductBacklog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductBacklogId() {
        return productBacklogId;
    }

    public void setProductBacklogId(String productBacklogId) {
        this.productBacklogId = productBacklogId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
