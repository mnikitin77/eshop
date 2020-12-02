package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-01T18:59:22+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setParent( categoryToCategoryDTO( category.getParent() ) );

        return categoryDTO;
    }

    @Override
    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setParent( categoryDTOToCategory( categoryDTO.getParent() ) );

        return category;
    }

    @Override
    public List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryDTO( category ) );
        }

        return list;
    }

    @Override
    public List<Category> categoryDTOsToCategories(List<CategoryDTO> categoryDTOs) {
        if ( categoryDTOs == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDTOs.size() );
        for ( CategoryDTO categoryDTO : categoryDTOs ) {
            list.add( categoryDTOToCategory( categoryDTO ) );
        }

        return list;
    }
}
