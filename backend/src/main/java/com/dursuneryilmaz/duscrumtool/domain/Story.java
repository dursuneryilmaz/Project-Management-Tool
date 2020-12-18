package com.dursuneryilmaz.duscrumtool.domain;

import java.util.List;

public class Story {
    private Long id;
    private String publicId;
    private String content;
    private String epicId;
    private Epic epic;
    private List<Task> taskList;
}
