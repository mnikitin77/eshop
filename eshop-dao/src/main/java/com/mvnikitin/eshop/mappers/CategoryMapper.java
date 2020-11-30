package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);

    List<Category> categoryDTOsToCategories(List<CategoryDTO> categoryDTOs);

}
