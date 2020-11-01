package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.CategoryDTO;
import com.mvnikitin.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryFormController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "category";
    }

    @PostMapping
    public String save(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id,
                         Model model) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
