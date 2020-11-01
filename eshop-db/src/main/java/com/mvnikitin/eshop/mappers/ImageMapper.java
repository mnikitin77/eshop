package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.model.Image;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "productDTO", ignore = true)
    ImageDTO imageToImageDTO(Image image);

    @Mapping(target = "product", ignore = true)
    Image imageDTOToImage(ImageDTO imageDTO);

    List<ImageDTO> imagesToImageDTOs(List<Image> images);

    List<Image> imageDTOsToImages(List<ImageDTO> imageDTOs);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "originalName", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "contentType", ignore = true)
    void updateDescriptionAndSelectedFromImageDTO(ImageDTO imageDTO, @MappingTarget Image image);
}
