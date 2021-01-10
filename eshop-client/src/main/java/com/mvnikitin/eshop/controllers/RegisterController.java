package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.RegisterForm;
import com.mvnikitin.eshop.dto.UserDTO;
import com.mvnikitin.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("regform", new RegisterForm());
        return "register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("regform")
                                       RegisterForm registerForm,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(registerForm.getUsername());
        userDTO.setPassword(registerForm.getPassword());
        userDTO.setFirstName(registerForm.getFirstName());
        userDTO.setMiddleName(registerForm.getMiddleName());
        userDTO.setLastName(registerForm.getLastName());
        userDTO.setPhone(registerForm.getPhone());
        userDTO.setEmail(registerForm.getEmail());

        userDTO.setIsBlocked(false);

        userService.newUser(userDTO);

        return "redirect:/login";
    }
}
