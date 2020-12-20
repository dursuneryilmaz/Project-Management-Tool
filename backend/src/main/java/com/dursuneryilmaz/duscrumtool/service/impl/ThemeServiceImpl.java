package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.ProductRepository;
import com.dursuneryilmaz.duscrumtool.repository.ThemeRepository;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    ThemeRepository themeRepository;
    @Autowired
    Utils utils;

    @Override
    public Theme createTheme(Theme theme, Product product) {
        theme.setThemeId(utils.generatePublicId(32));
        theme.setProduct(product);
        return themeRepository.save(theme);
    }

    @Override
    public List<Theme> getAllByProduct(Product product) {
        List<Theme> themeList = themeRepository.findAllByProduct(product);
        if (themeList.size() == 0)
            throw new ProductIdException(ExceptionMessages.NO_RECORDS_FOUND.getExceptionMessage());
        return themeList;
    }

    @Override
    public Theme getByThemeId(String themeId) {
        Theme theme = themeRepository.findByThemeId(themeId);
        if (theme == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return theme;
    }
}
