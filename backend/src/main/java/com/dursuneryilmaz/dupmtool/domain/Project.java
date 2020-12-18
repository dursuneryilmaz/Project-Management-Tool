package com.dursuneryilmaz.dupmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Project name cannot be blank!")
    private String projectName;
    @NotBlank(message = "Project code cannot be blank!")
    @Size(min = 3, max = 8, message = "Please use 3 to 8 characters!")
    @Column(updatable = false)
    private String projectCode;
    @NotBlank(message = "Project description cannot be blank!")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date endDate;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date updatedAt;

    public Project() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
