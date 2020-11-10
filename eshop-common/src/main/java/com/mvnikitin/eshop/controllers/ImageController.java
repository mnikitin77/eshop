package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Value("${eshop.file.storage}")
    private String fileStorage;

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model) {
        ImageDTO imageDTO = imageService.findById(id);
        model.addAttribute("image", imageDTO);
        model.addAttribute("product_id", imageDTO.getProductDTO().getId());
        return "image";
    }

    @PostMapping
    public String save(@ModelAttribute ImageDTO imageDTO) throws IOException {
        ImageDTO savedImageDTO = imageService.save(imageDTO);
        return "redirect:/product/" + savedImageDTO.getProductDTO().getId();
    }

    @GetMapping("download/{id}")
    public void downloadProductPicture(
            @PathVariable("id") Integer id,
            HttpServletResponse response) throws IOException {

        ImageDTO imageDTO = imageService.findById(id);

        if (imageDTO != null) {

            try(InputStream stream = Files.newInputStream(
                    Paths.get(fileStorage, imageDTO.getName()));) {

                response.setContentType(imageDTO.getContentType());
                response.getOutputStream().write(stream.readAllBytes());
            }
        }

        return;
    }

    @DeleteMapping("/{id}/product/{productId}/delete")
    public String delete(@PathVariable(value = "id") Integer id,
                         @PathVariable(value = "productId") Integer productId) throws IOException {
        imageService.deleteById(id);
        return "redirect:/product/" + productId;
    }
}
