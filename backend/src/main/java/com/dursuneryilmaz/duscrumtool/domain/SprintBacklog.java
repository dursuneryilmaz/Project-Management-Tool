package com.dursuneryilmaz.duscrumtool.domain;

import java.io.Serializable;
import java.util.List;

public class SprintBacklog implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -4146812475448599641L;
    private Long id;
    private String publicId;
    private String sprintId;
    private Sprint sprint;
    private List<Task> taskList;
}

