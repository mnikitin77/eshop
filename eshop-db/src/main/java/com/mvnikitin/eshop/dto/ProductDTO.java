package com.mvnikitin.eshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Integer id;
    private String code;
    private String name;
    private BigDecimal price;
    private Boolean isActive;
    private CategoryDTO category;
    private BrandDTO brand;

    private List<ImageDTO> images;

    private MultipartFile file;
}
