package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class CatalogueController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("products", productService.findAllActive());
        return "catalogue";
    }

    @GetMapping("/{id}")
    public  String showByCategory(@PathVariable(value = "id") Integer id,
                                  Model model) {
        model.addAttribute("products", productService.findAllByCategoryId(id));
        return "catalogue";
    }
}
