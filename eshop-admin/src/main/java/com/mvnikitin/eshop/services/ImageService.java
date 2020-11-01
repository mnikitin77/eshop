package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ImageDTO;

import java.io.IOException;

public interface ImageService {
    ImageDTO findById(Integer id);
    void deleteById(Integer id);
    ImageDTO save(ImageDTO imageDTO) throws IOException;
}
