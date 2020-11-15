package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.dto.ProductDTO;
import com.mvnikitin.eshop.mappers.ProductMapper;
import com.mvnikitin.eshop.model.Image;
import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final Sort sortByNameAsc = Sort.sort(Product.class)
            .by("name").ascending();

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ImageService imageService;

    @Value("${eshop.file.storage}")
    private String fileStorage;

    @Value("${eshop.default_items_per_page}")
    private String defaultItemsPerPage;

    @Value("${eshop.default_sort_by}")
    private String defaultSortBy;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO findById(Integer id) {
        return productMapper.productToProductDTO(
                productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product [id:" +
                        id + "] not found")));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.productsToProductDTOs(
                productRepository.findAll());
    }

    @Override
    public List<ProductDTO> findAllActive() {
        return productMapper.productsToProductDTOs(
                productRepository.findAllByIsActiveTrue());
    }

    @Override
    public List<ProductDTO> findAllByCategoryIdActive(Integer id) {
        return productMapper.productsToProductDTOs(
                productRepository.findAllByCategoryIdAndIsActiveTrue(
                        id, sortByNameAsc));
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) throws IOException {

        Product product =
                productMapper .productDTOToProduct(productDTO);

        MultipartFile mpFile = productDTO.getFile();
        if (!mpFile.isEmpty()) {

            String storedFileName = UUID.randomUUID() +
                    mpFile.getOriginalFilename();
            Path path = Paths.get(fileStorage, storedFileName);

            mpFile.transferTo(path);

            if (product.getImages() == null) {
                product.setImages(new ArrayList<>());
            }

            Image image = new Image();
            image.setName(storedFileName);
            image.setOriginalName(mpFile.getOriginalFilename());
            image.setContentType(mpFile.getContentType());
            image.setSize((int) mpFile.getSize());
            image.setSelected(false);
            image.setProduct(product);

            product.getImages().add(image);
        }

        return productMapper.productToProductDTO(
                productRepository.save(
                        product));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws IOException {
        // Deleting images manually in order
        // to remove the image files.
        ProductDTO productDTO = findById(id);
        for (ImageDTO imageDTO: productDTO.getImages()) {
            imageService.deleteById(imageDTO.getId());
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> getItemsByPage(BigDecimal priceMin,
                                        BigDecimal priceMax,
                                        Integer pageNumber,
                                        Integer rowsPerPage,
                                        String sortBy,
                                        Integer categoryId) {

        Sort sort = Sort.sort(Product.class)
                .by(sortBy)
                .ascending();

        Pageable pageable = PageRequest.of(
                pageNumber != null ? pageNumber - 1 : 0,
                rowsPerPage,
                sort);

        if (categoryId != null) {
            return productRepository.findByIsActiveTrueAndCategoryIdAndPriceBetween(
                    categoryId,
                    priceMin,
                    priceMax,
                    pageable)
                .map(prod -> productMapper.productToProductDTO((Product)prod));
        } else {
            return productRepository.findByIsActiveTrueAndPriceBetween(
                    priceMin, priceMax, pageable)
                .map(prod -> productMapper.productToProductDTO((Product)prod));
        }
    }
}
