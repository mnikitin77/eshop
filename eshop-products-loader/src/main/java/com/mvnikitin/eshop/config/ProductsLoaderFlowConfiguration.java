package com.mvnikitin.eshop.config;

import com.mvnikitin.eshop.model.Product;
import com.mvnikitin.eshop.services.CSVToProductTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;

import javax.persistence.EntityManagerFactory;
import java.io.File;

@Configuration
public class ProductsLoaderFlowConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductsLoaderFlowConfiguration.class);

    @Value("${eshop.data.upload.directory.product}")
    private String sourceDirectoryPath;

    @Value("${eshop.data.upload.pattern_filter}")
    private String patternFilter;

    @Value("${eshop.data.upload.time.interval}")
    private Integer directoryScanInterval;

    private CSVToProductTransformer transformer;

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setTransformer(CSVToProductTransformer transformer) {
        this.transformer = transformer;
        this.transformer.setDeleteFiles(true);
    }

    @Autowired
    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public IntegrationFlow uploadFromFileFlow() {
        return IntegrationFlows
                .from(Files.inboundAdapter(new File(sourceDirectoryPath))
                                .autoCreateDirectory(true)
                                .patternFilter(patternFilter),
                        e -> e.poller(
                                Pollers.fixedDelay(directoryScanInterval)))
                .transform(transformer)
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                        .entityClass(Product.class)
                        .persistMode(PersistMode.PERSIST),
                        e -> e.transactional(true))
                .get();
    }
}
