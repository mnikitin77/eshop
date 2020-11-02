package com.mvnikitin.eshop.dto;

import lombok.Data;

@Data
public class ImageDTO {

    private Integer id;
    private String name;
    private String originalName;
    private String description;
    private Integer size;
    private String contentType;
    private Boolean selected;

    private ProductDTO productDTO;
}
