package com.mvnikitin.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandsController {

    @GetMapping("/brands")
    public String show() {
        return "brands";
    }
}
