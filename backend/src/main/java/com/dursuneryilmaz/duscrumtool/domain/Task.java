package com.dursuneryilmaz.duscrumtool.domain;

import java.util.Date;

public class Task {
    private Long id;
    private String publicId;
    private String content;
    private String storyId;
    private Story story;
    private String status;
    private Integer priority;
    private Date dueDate;
    private String userId;
    private User attendant;
}
