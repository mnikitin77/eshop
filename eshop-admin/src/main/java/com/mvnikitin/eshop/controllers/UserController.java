package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.UserDTO;
import com.mvnikitin.eshop.services.RoleService;
import com.mvnikitin.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public String save(@ModelAttribute UserDTO userDTO) {
        userService.saveWithoutPassword(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id) throws IOException {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
