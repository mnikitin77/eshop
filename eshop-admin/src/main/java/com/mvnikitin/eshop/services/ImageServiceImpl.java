package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.ImageMapper;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Image;
import com.mvnikitin.eshop.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;
    private ProductMapper productMapper;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ImageDTO findById(Integer id) {
        return imageMapper.imageToImageDTO(
                imageRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Image [id:" +
                        id + "] not found")));
    }

    @Override
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }

    @Override
    public ImageDTO save(ImageDTO imageDTO) throws IOException {

        Image image = imageRepository.findById(imageDTO.getId()).orElseThrow(
                () -> new RuntimeException("Image [id:" +
                        imageDTO.getId() + "] not found"));

        // Not affecting the 'product' field
        imageMapper.updateDescriptionAndSelectedFromImageDTO(imageDTO, image);

        ProductDTO productDTO = productMapper.productToProductDTO(image.getProduct());

        ImageDTO retval = imageMapper.imageToImageDTO(
                imageRepository.save(
                        image));
        retval.setProductDTO(productDTO);

        return retval;
    }
}
