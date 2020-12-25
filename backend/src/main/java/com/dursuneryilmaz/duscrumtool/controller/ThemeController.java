package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Product;
import com.dursuneryilmaz.duscrumtool.domain.Theme;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.service.EpicService;
import com.dursuneryilmaz.duscrumtool.service.ProductService;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/themes")
public class ThemeController {
    @Autowired
    ThemeService themeService;
    @Autowired
    ProductService productService;
    @Autowired
    EpicService epicService;
    @Autowired
    RequestValidationService requestValidationService;

    @PostMapping(path = "/{productId}")
    public ResponseEntity<?> createTheme(@PathVariable String productId, @Valid @RequestBody Theme theme, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;
        Product product = productService.getProductById(productId);
        return new ResponseEntity<Theme>(themeService.createTheme(theme, product), HttpStatus.CREATED);
    }

    // get theme's epics
    @GetMapping(path = "/{themeId}/epics")
    public ResponseEntity<List<Epic>> getThemeEpics(@PathVariable String themeId) {
        Theme theme = themeService.getThemeById(themeId);
        return new ResponseEntity<List<Epic>>(epicService.getAllByTheme(theme), HttpStatus.OK);
    }
}
