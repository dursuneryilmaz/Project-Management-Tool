package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;

import java.util.List;

public interface ThemeService {
    Theme createTheme(Theme theme, Product product);

    Theme getThemeById(String themeId);

    List<Theme> getAllByProduct(Product product);

    Theme updateThemeById(String themeId, Theme theme);

    Boolean deleteThemeById(String themeId);
}
