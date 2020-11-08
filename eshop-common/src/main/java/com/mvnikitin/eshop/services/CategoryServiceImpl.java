package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.mappers.CategoryMapper;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO findById(Integer id) {
        return categoryMapper.categoryToCategoryDTO(
                categoryRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Category [id:" +
                                id + "] not found")));
    }

    @Override
    public List<CategoryDTO> findAll() {

        Sort sort = Sort.sort(Category.class)
                .by("name").ascending();

        return categoryMapper.categoriesToCategoryDTOs(
                categoryRepository.findAll(sort));
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return categoryMapper.categoryToCategoryDTO(
                categoryRepository.save(
                        categoryMapper.categoryDTOToCategory(categoryDTO)));
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
