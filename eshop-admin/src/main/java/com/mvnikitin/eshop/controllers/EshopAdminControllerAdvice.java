package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.services.BrandService;
import com.mvnikitin.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class EshopAdminControllerAdvice {

    private CategoryService categoryService;
    private BrandService brandService;

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
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
    }
}
