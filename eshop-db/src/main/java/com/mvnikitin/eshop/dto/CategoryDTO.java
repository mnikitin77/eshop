package com.mvnikitin.eshop.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private CategoryDTO parent;
}
