package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;

import java.util.List;

public interface StoryService {
    Story createStory(Story story, Epic epic);

    Story getStoryById(String storyId);

    List<Story> getAllByEpic(Epic epic);

    Story updateStoryById(String storyId, Story story);

    Boolean deleteStoryById(String storyId);
}
