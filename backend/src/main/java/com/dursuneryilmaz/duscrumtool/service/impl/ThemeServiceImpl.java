package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.exception.ProjectCodeException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ThemeRepository;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    ThemeRepository themeRepository;

    @Override
    public List<Theme> findAllByProduct(Product product) {
        List<Theme> themeList = themeRepository.findAllByProduct(product);
        if (themeList == null) throw new ProjectCodeException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
        return themeList;
    }

    @Override
    public Theme findByThemeId(String themeId) {
        Theme theme = themeRepository.findByThemeId(themeId);
        if (theme == null) throw new ProjectCodeException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return theme;
    }
}
