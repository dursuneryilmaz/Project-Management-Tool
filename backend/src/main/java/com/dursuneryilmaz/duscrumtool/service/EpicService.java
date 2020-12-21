package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Theme;

import java.util.List;

public interface EpicService {
    Epic createEpic(Epic epic, Theme theme);

    Epic getEpicById(String epicId);

    List<Epic> getAllByTheme(Theme theme);

    Epic updateEpicById(String epicId, Epic epic);

    Boolean deleteEpicById(String epicId);
}
