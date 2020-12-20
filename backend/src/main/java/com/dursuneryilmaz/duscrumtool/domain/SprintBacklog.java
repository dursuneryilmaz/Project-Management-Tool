package com.dursuneryilmaz.duscrumtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
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
}

