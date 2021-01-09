package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.shared.enums.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.StoryRepository;
import com.dursuneryilmaz.duscrumtool.service.StoryService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    Utils utils;

    @Override
    public Story createStory(Story story, Epic epic) {
        story.setStoryId(utils.generatePublicId(32));
        story.setEpic(epic);
        return storyRepository.save(story);
    }

    @Override
    public Story getStoryById(String storyId) {
        return checkStoryExistenceById(storyId);
    }

    @Override
    public List<Story> getAllByEpic(Epic epic) {
        List<Story> storyList = storyRepository.findAllByEpic(epic);
        if (storyList.size() == 0)
            throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
        return storyList;
    }

    @Override
    public Story updateStoryById(String storyId, Story story) {
        Story storyToUpdate = checkStoryExistenceById(storyId);
        BeanUtils.copyProperties(story, storyToUpdate);
        return storyRepository.save(storyToUpdate);
    }

    @Override
    public Boolean deleteStoryById(String storyId) {
        Story story = checkStoryExistenceById(storyId);
        storyRepository.delete(story);
        return true;
    }

    private Story checkStoryExistenceById(String storyId) {
        Story story = storyRepository.findByStoryId(storyId);
        if (story != null) return story;
        throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
    }
}
