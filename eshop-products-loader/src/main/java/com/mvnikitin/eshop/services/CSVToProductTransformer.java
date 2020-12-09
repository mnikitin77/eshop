package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.model.Brand;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.repositories.BrandRepository;
import com.mvnikitin.eshop.repositories.CategoryRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.file.transformer.AbstractFilePayloadTransformer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class CSVToProductTransformer extends AbstractFilePayloadTransformer<List<Product>> {

    private static final Logger logger =
            LoggerFactory.getLogger(CSVToProductTransformer.class);

    @Value("${eshop.data.upload.separator}")
    private String separator;

    private CSVParser parser;

    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    private Map<Integer, Category> categories = new HashMap<>();
    private Map<Integer, Brand> brands = new HashMap<>();

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {

        parser = new CSVParserBuilder()
                        .withSeparator(separator.charAt(0))
                        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        List<Category> cats = categoryRepository.findAll();
        for (Category c: cats) {
            categories.put(c.getId(), c);
        }

        List<Brand> brs = brandRepository.findAll();
        for(Brand b: brs) {
            brands.put(b.getId(), b);
        }
    }

    @Override
    protected List<Product> transformFile(File file) {

        List<Product> products = null;
        
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1)
                     .withCSVParser(parser)
                     .build();) {

            products = new ArrayList<>();
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                products.add(getProductFromStringsArray(line));
            }

            logger.info("File {} is converted, total lines {}",
                    file.getName(), products.size());

        } catch (Exception e) {
            logger.error("Exception thrown when converting the file:" +
                    " {}, message: {}, root cause: {}",
                    file.getName(), e.getMessage(), e.getCause().getMessage());
        }

        return products;
    }

    private Product getProductFromStringsArray(String[] fileLineItems) {

        if (fileLineItems.length != 8) {
            throw new RuntimeException("Incorrent number of columns, " +
                    "there must be 8");
        }

        Product product = new Product();

        try {
            product.setCategory(categories.get(
                    Integer.valueOf(fileLineItems[0])));
            product.setBrand(brands.get(
                    Integer.valueOf(fileLineItems[1])));
            product.setCode(fileLineItems[2]);
            product.setName(fileLineItems[3]);
            product.setDescription(fileLineItems[4]);
            product.setPrice(new BigDecimal(fileLineItems[5]));
            if (fileLineItems[6] != null) {
                product.setOldPrice(new BigDecimal(fileLineItems[6]));
            }
            product.setIsActive(Boolean.valueOf(fileLineItems[7]));
        } catch (Exception e) {
            throw new RuntimeException("Exception when converting a file " +
                    "line to Product entity, line: " +
                    Arrays.toString(fileLineItems), e);
        }

        return product;
    }
}
