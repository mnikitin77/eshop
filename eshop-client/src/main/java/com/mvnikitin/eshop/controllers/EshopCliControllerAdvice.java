package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.CatalogueFilter;
import com.mvnikitin.eshop.dto.CategoryTreeDTO;
import com.mvnikitin.eshop.dto.ShoppingCart;
import com.mvnikitin.eshop.model.ICategoryCount;
import com.mvnikitin.eshop.repositories.BrandCountRepository;
import com.mvnikitin.eshop.repositories.CategoryCountRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EshopCliControllerAdvice {

    private final static Logger log =
            LoggerFactory.getLogger(EshopCliControllerAdvice.class);

    private CategoryCountRepository categoryCountRepository;
    private BrandCountRepository brandCountRepository;

    private EurekaClient eureka;

    @Value("${eshop.image_default}")
    String defaultImageName;
    @Value("${eshop.default_items_per_page}")
    Integer defaultItemsPerPage;
    @Value("${eshop.default_sort_by}")
    String defaultSortBy;
    @Value("${eshop.default_page}")
    Integer defaultPage;
    @Value("${eshop.default_minprice}")
    BigDecimal defaultMinPrice;
    @Value("${eshop.default_maxprice}")
    BigDecimal defaultMaxPrice;

    @Autowired
    public void setCategoryCountRepository(
            CategoryCountRepository categoryCountRepository) {
        this.categoryCountRepository = categoryCountRepository;
    }

    @Autowired
    public void setEureka(EurekaClient eureka) {
        this.eureka = eureka;
    }

    @Autowired
    public void setBrandCountRepository(BrandCountRepository brandCountRepository) {
        this.brandCountRepository = brandCountRepository;
    }

    @ModelAttribute("filter")
    public CatalogueFilter catalogueFilter() {
        return new CatalogueFilter();
    }

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        String imageServiceURL = null;

        try {
            InstanceInfo server = eureka.getNextServerFromEureka("GATEWAY", false);
            imageServiceURL = server.getHomePageUrl() + "image-service";
        } catch (RuntimeException ex) {
            log.error("Unable to discover GATEWAY url", ex);
        }

        model.addAttribute("image_service", imageServiceURL);

        model.addAttribute("default_image_name",defaultImageName);
        model.addAttribute("default_items_number",defaultItemsPerPage);
        model.addAttribute("default_sort_by", defaultSortBy);
        model.addAttribute("default_sort_by", defaultPage);
        model.addAttribute("default_minprice", defaultMinPrice);
        model.addAttribute("default_maxprice", defaultMaxPrice);

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
