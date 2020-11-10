package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/product")
public class ItemController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(("/{id}"))
    public String show(@PathVariable(value = "id") Integer id,
                       Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }
}
