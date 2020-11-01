package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brand")
public class BrandFormController {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("brand", new BrandDTO());
        return "brand";
    }

    @PostMapping
    public String save(@ModelAttribute BrandDTO brandDTO) {
        brandService.save(brandDTO);
        return "redirect:/brands";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        model.addAttribute("brand", brandService.findById(id));
        return "brand";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id,
                         Model model) {
        brandService.deleteById(id);
        return "redirect:/brands";
    }
}
