package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String show(Model model) {
        ProductDTO productDTO = new ProductDTO();
        // Setting the "active" atribute to be true in the database
        productDTO.setIsActive(true);
        model.addAttribute("product", productDTO);
        return "product";
    }

    @PostMapping
    public String save(@ModelAttribute ProductDTO productDTO) throws IOException {
        productService.save(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id) throws IOException {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
