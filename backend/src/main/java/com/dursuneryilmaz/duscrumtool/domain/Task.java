package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 32, max = 32)
    private String taskId;
    @NotBlank(message = "Task content can not be blank!")
    private String content;
    @NotBlank(message = "Task status can not be blank!")
    private String status;
    private Integer priority;
    private Date dueDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_backlog_id")
    ProductBacklog productBacklog;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sprint_backlog_id")
    SprintBacklog sprintBacklog;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User attendant;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public User getAttendant() {
        return attendant;
    }

    public void setAttendant(User attendant) {
        this.attendant = attendant;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public void setSprintBacklog(SprintBacklog sprintBacklog) {
        this.sprintBacklog = sprintBacklog;
    }
}
