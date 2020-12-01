package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.mappers.ImageMapper;
import com.mvnikitin.eshop.model.Image;
import com.mvnikitin.eshop.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageDownloadServiceImpl implements ImageDownloadService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public ImageDTO findById(Integer id) {

        Image image = imageRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Image [id:" +
                        id + "] not found"));

        return imageMapper.imageToImageDTO(image);
    }
}
