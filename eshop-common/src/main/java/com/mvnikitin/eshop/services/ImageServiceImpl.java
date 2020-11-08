package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.ImageMapper;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Image;
import com.mvnikitin.eshop.repositories.ImageRepository;
import com.mvnikitin.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;
    private ProductMapper productMapper;
    private ProductRepository productRepository;

    @Value("${eshop.file.storage}")
    private String fileStorage;

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

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ImageDTO findById(Integer id) {

        Image image = imageRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Image [id:" +
                        id + "] not found"));
        ProductDTO productDTO =
                productMapper.productToProductDTO(image.getProduct());

        ImageDTO imageDTO = imageMapper.imageToImageDTO(image);
        imageDTO.setProductDTO(productDTO);

        return imageDTO;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws IOException {

        Image image = imageRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Image [id:" +
                        id + "] not found"));

        Path path = Paths.get(fileStorage, image.getName());
        if (Files.exists(path)) {
            Files.delete(Paths.get(fileStorage, image.getName()));
        }

        imageRepository.deleteById(id);
    }

    @Override
    @Transactional
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
