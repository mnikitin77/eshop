package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDTO brandToBrandDTO(Brand brand);

    Brand brandDTOToBrand(BrandDTO brandDTO);

    List<BrandDTO> brandsToBrandDTOs(List<Brand> brand);

    List<Brand> brandDTOsToBrands(List<BrandDTO> brandDTOs);

}
