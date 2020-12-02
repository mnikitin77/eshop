package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.services.BrandService;
import com.mvnikitin.eshop.services.CategoryService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class EshopAdminControllerAdvice {

    private final static Logger log =
            LoggerFactory.getLogger(EshopAdminControllerAdvice.class);

    private EurekaClient eureka;

    private CategoryService categoryService;
    private BrandService brandService;

    @Autowired
    public void setEureka(EurekaClient eureka) {
        this.eureka = eureka;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
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

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
    }
}
