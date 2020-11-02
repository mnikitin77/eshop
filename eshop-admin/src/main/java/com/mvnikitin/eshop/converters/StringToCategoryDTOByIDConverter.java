package com.mvnikitin.eshop.converters;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryDTOByIDConverter implements Converter<String, CategoryDTO> {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDTO convert(String id) {

        if (id != null && Character.isDigit(id.charAt(0))) {
            return categoryService.findById(Integer.valueOf(id));
        }

        return null;
    }
}
