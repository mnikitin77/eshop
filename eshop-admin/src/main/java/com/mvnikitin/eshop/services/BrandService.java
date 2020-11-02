package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.BrandDTO;

import java.util.List;

public interface BrandService {
    BrandDTO findById(Integer id);
    List<BrandDTO> findAll();
    BrandDTO save(BrandDTO brandDTO);

    void deleteById(Integer id);
}
