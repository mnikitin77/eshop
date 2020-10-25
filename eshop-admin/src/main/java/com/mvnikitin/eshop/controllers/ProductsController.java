package com.mvnikitin.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @GetMapping("/products")
    public String show() {
        return "products";
    }
}
