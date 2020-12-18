package com.dursuneryilmaz.duscrumtool.domain;

import java.util.List;

public class SprintBacklog {
    private Long id;
    private String publicId;
    private String sprintId;
    private Sprint sprint;
    private List<Task> taskList;
}

