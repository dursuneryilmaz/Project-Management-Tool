package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.EpicRepository;
import com.dursuneryilmaz.duscrumtool.service.EpicService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicServiceImpl implements EpicService {
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    Utils utils;

    @Override
    public Epic createEpic(Epic epic, Theme theme) {
        epic.setEpicId(utils.generatePublicId(32));
        epic.setTheme(theme);
        return epicRepository.save(epic);
    }

    @Override
    public Epic getEpicById(String epicId) {
        return checkEpicExistenceById(epicId);
    }

    @Override
    public List<Epic> getAllByTheme(Theme theme) {
        List<Epic> epicList = epicRepository.findAllByTheme(theme);
        if (epicList.size() == 0)
            throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
        return epicList;
    }

    @Override
    public Epic updateEpicById(String epicId, Epic epic) {
        Epic epicToUpdate = checkEpicExistenceById(epicId);
        // check later entity updatable false
        BeanUtils.copyProperties(epic, epicToUpdate);
        return epicRepository.save(epicToUpdate);
    }

    @Override
    public Boolean deleteEpicById(String epicId) {
        Epic epic = checkEpicExistenceById(epicId);
        epicRepository.delete(epic);
        return true;
    }

    private Epic checkEpicExistenceById(String epicId) {
        Epic epic = epicRepository.findByEpicId(epicId);
        if (epic == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return epic;
    }
}
