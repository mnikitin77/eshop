package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeDTO {
    Integer id;
    Integer parentId;
    String name;
    Integer productsCount;
    List<CategoryTreeDTO> children;
}
