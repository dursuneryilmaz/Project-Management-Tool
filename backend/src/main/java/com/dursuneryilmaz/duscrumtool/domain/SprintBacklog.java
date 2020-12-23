package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sprint_backlogs")
public class SprintBacklog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sprintBacklogId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
    @JsonIgnore
    @OneToMany(mappedBy = "sprintBacklog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> taskList;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
    private Date updateDate;

    public SprintBacklog() {
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprintBacklogId() {
        return sprintBacklogId;
    }

    public void setSprintBacklogId(String sprintBacklogId) {
        this.sprintBacklogId = sprintBacklogId;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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

