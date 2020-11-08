package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.CategoryTreeDTO;
import com.mvnikitin.eshop.model.ICategoryCount;
import com.mvnikitin.eshop.repositories.BrandCountRepository;
import com.mvnikitin.eshop.repositories.CategoryCountRepository;
import com.mvnikitin.eshop.services.BrandService;
import com.mvnikitin.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EshopCliControllerAdvice {

    CategoryCountRepository categoryCountRepository;
    BrandCountRepository brandCountRepository;

    @Value("${eshop.image_default}")
    String defaultImageName;

    @Autowired
    public void setCategoryCountRepository(
            CategoryCountRepository categoryCountRepository) {
        this.categoryCountRepository = categoryCountRepository;
    }

    @Autowired
    public void setBrandCountRepository(BrandCountRepository brandCountRepository) {
        this.brandCountRepository = brandCountRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("default_image_name",defaultImageName);
        model.addAttribute("cats", getCategoryTreeDTOS());
        model.addAttribute("brands",
                brandCountRepository.countTotalProductsByBrandNative());
    }


    private List<CategoryTreeDTO> getCategoryTreeDTOS() {
        List<CategoryTreeDTO> retval = null;

        List<ICategoryCount> categoryCounts = categoryCountRepository.countTotalProductsByCategoryNative();
        List<CategoryTreeDTO> categoryTreeDTOS = new ArrayList<>(categoryCounts.size());

        for (ICategoryCount c: categoryCounts) {

            CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO();
            categoryTreeDTO.setId(c.getId());
            categoryTreeDTO.setParentId(c.getParentId());
            categoryTreeDTO.setName(c.getName());
            categoryTreeDTO.setProductsCount(c.getTotal());

            categoryTreeDTOS.add(categoryTreeDTO);
        }

        categoryTreeDTOS.stream()
                .sorted(Comparator.comparing(CategoryTreeDTO::getName))
                .forEach(cdto -> {
                    categoryTreeDTOS.stream()
                            .filter(item -> item.getParentId() == cdto.getId())
                            .forEach(child -> {
                                // 1. add a child
                                if (cdto.getChildren() == null) {
                                    cdto.setChildren(new ArrayList<>());
                                }
                                cdto.getChildren().add(child);

                                // 2. aggregate the products total count
                                cdto.setProductsCount(cdto.getProductsCount() +
                                        child.getProductsCount());
                            });
                });

        // Remove the children off the root level
        retval = categoryTreeDTOS.stream()
                .filter(cdto -> cdto.getParentId() == null)
                .collect(Collectors.toList());

        retval.sort(Comparator.comparing(CategoryTreeDTO::getName));
        retval.stream()
                .forEach(cdto -> {
                    if (cdto.getChildren() != null) {
                        cdto.getChildren().sort(Comparator.comparing(
                                CategoryTreeDTO::getName));
                    }
                });

        return retval;
    }
}
