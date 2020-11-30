package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO  implements Serializable {

    private static final long serialVersionUID = -7582017203375014541L;

    private Integer id;
    private String name;
    private CategoryDTO parent;
}
