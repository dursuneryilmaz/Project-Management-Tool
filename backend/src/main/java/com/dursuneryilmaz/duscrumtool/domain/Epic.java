package com.dursuneryilmaz.duscrumtool.domain;

import java.util.List;

public class Epic {
    private Long id;
    private String publicId;
    private String content;
    private String themeId;
    private Theme theme;
    private List<Story> storyList;
}
