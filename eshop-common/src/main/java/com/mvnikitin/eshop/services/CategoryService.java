package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO findById(Integer id);
    List<CategoryDTO> findAll();
    CategoryDTO save(CategoryDTO categoryDTO);
    void deleteById(Integer id);
}
