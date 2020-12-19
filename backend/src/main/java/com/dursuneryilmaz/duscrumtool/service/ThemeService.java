package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;

import java.util.List;

public interface ThemeService {
    List<Theme> findAllByProduct(Product product);
    Theme findByThemeId(String themeId);
}
