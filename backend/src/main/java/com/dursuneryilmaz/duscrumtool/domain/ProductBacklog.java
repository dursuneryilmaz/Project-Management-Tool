package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> taskList = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date updateDate;

    public ProductBacklog() {
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
