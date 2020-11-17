package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.CatalogueFilter;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/shop")
@SessionAttributes(value = {"filter", "cart"})
public class CatalogueController {

    private ProductService productService;

    @Value("${eshop.default_items_per_page}")
    Integer defaultItemsPerPage;
    @Value("${eshop.default_sort_by}")
    String defaultSortBy;
    @Value("${eshop.default_page}")
    Integer defaultPage;
    @Value("${eshop.default_minprice}")
    BigDecimal defaultMinPrice;
    @Value("${eshop.default_maxprice}")
    BigDecimal defaultMaxPrice;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String show(Model model,
                       @ModelAttribute("filter")  CatalogueFilter filter) {

        if (!filter.isApplied()) {

            filter.setMaxPrice(defaultMaxPrice);
            filter.setMinPrice(defaultMinPrice);
            filter.setRows(defaultItemsPerPage);
            filter.setCurrent(1);
            filter.setSortBy(defaultSortBy);
        }

        Page<ProductDTO> products = productService.getItemsByPage(
                filter.getMinPrice() != null? filter.getMinPrice() : defaultMinPrice,
                filter.getMaxPrice() != null? filter.getMaxPrice() : defaultMaxPrice,
                filter.getCurrent() != null? filter.getCurrent() : defaultPage,
                filter.getRows() != null? filter.getRows() : defaultItemsPerPage,
                filter.getSortBy() != null? filter.getSortBy() : defaultSortBy,
                null);

        model.addAttribute("products", products);

        return "catalogue";
    }

    @GetMapping("/category/{id}")
    public  String showByCategory(@PathVariable(value = "id") Integer id,
                                  @ModelAttribute("filter")  CatalogueFilter filter,
                                  Model model) {

        Page<ProductDTO> products = productService.getItemsByPage(
                filter.getMinPrice() != null? filter.getMinPrice() : defaultMinPrice,
                filter.getMaxPrice() != null? filter.getMaxPrice() : defaultMaxPrice,
                filter.getCurrent() != null? filter.getCurrent() : defaultPage,
                filter.getRows() != null? filter.getRows() : defaultItemsPerPage,
                filter.getSortBy() != null? filter.getSortBy() : defaultSortBy,
                id);

        model.addAttribute("products", products);

        return "catalogue";
    }

    @GetMapping("/category/page/{p}")
    public  String showByCategory(@PathVariable(value = "p") Integer p,
                                  @ModelAttribute("filter")  CatalogueFilter filter) {
        filter.setCurrent(p);
        filter.setApplied(true);
        return "redirect:/shop";
    }

    @PostMapping
    public RedirectView applyFilter(
            @ModelAttribute("filter")  CatalogueFilter filter,
            RedirectAttributes attributes) {

        filter.setApplied(true);
        attributes.addFlashAttribute("filter", filter);

        return new RedirectView("shop", true);
    }

    @GetMapping("/reset")
    public String resetFilter(
            @ModelAttribute("filter")  CatalogueFilter filter) {

        filter.setApplied(false);
        return "redirect:/shop";
    }
}

