package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeDTO {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer productsCount;
    private List<CategoryTreeDTO> children;
}
