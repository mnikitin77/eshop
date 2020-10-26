package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminsController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admins")
    public String show(Model model) {
        model.addAttribute("users", userService.findAllAdmins());
        return "admins";
    }
}
