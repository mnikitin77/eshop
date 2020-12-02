package com.mvnikitin.eshop.controllers;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.services.ImageDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
public class ImageDownloadController {

    @Value("${eshop.file.storage}")
    private String fileStorage;

    private ImageDownloadService imageService;

    @Autowired
    public void setImageService(ImageDownloadService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public void downloadProductImage(
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
    }
}
