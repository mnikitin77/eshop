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

    private final Sort sortByNameAsc = Sort.sort(Category.class)
            .by("name").ascending();

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
        return categoryMapper.categoriesToCategoryDTOs(
                categoryRepository.findAll(sortByNameAsc));
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
