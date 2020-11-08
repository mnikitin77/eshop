package com.mvnikitin.eshop.converters;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBrandDTOByIDConverter implements Converter<String, BrandDTO> {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public BrandDTO convert(String id) {

        if (id != null && Character.isDigit(id.charAt(0))) {
            return brandService.findById(Integer.valueOf(id));
        }

        return null;
    }
}
