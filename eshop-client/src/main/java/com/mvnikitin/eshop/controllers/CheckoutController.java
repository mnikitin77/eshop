package com.mvnikitin.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @GetMapping
    public String show() {
        return "checkout";
    }
}
