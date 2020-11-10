package com.mvnikitin.eshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Data
public class ProductDTO {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Boolean isActive;
    private CategoryDTO category;
    private BrandDTO brand;

    private List<ImageDTO> images;

    private MultipartFile file;

    public ImageDTO getPrimaryImage() {

        if (images != null && images.size() > 0) {
            Optional<ImageDTO> primaryImage = images.stream()
                    .filter(i -> i.getSelected())
                    .findFirst();

            return primaryImage.isPresent() ?
                    primaryImage.get() :
                    images.get(0);
        }

        return null;
    }
}
