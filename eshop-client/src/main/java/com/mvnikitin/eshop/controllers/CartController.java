package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.ShoppingCart;
import com.mvnikitin.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String show() {
        return "cart";
    }

    @GetMapping("/{id}/add")
    public String addItem(@PathVariable(value = "id") Integer id,
                          @ModelAttribute("cart") ShoppingCart cart) {

        cart.addOrModifyItem(productService.findById(id), 1);
        return "redirect:/shop";
    }

    @GetMapping("/add")
    public String addItemFromProductPage(@RequestParam(value = "id")
                                                     Integer id,
                                         @RequestParam(value = "quantity",
                                                 defaultValue = "1")
                                                 Integer quantity,
                          @ModelAttribute("cart") ShoppingCart cart) {

        cart.addOrModifyItem(productService.findById(id), quantity);
        return "redirect:/shop";
    }

    @PostMapping
    public String update(@ModelAttribute("cart") ShoppingCart cart) {
        cart.update();
        return "redirect:/cart";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id,
                         @ModelAttribute("cart") ShoppingCart cart) {
        cart.removeItem(productService.findById(id));
        return "redirect:/cart";
    }
}
