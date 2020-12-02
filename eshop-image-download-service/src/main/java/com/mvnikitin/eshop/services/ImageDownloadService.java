package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.ImageDTO;

public interface ImageDownloadService {
    ImageDTO findById(Integer id);
}
