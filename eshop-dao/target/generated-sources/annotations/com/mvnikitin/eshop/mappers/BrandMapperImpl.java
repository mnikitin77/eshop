package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.model.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-01T18:59:22+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDTO brandToBrandDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setId( brand.getId() );
        brandDTO.setName( brand.getName() );

        return brandDTO;
    }

    @Override
    public Brand brandDTOToBrand(BrandDTO brandDTO) {
        if ( brandDTO == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDTO.getId() );
        brand.setName( brandDTO.getName() );

        return brand;
    }

    @Override
    public List<BrandDTO> brandsToBrandDTOs(List<Brand> brand) {
        if ( brand == null ) {
            return null;
        }

        List<BrandDTO> list = new ArrayList<BrandDTO>( brand.size() );
        for ( Brand brand1 : brand ) {
            list.add( brandToBrandDTO( brand1 ) );
        }

        return list;
    }

    @Override
    public List<Brand> brandDTOsToBrands(List<BrandDTO> brandDTOs) {
        if ( brandDTOs == null ) {
            return null;
        }

        List<Brand> list = new ArrayList<Brand>( brandDTOs.size() );
        for ( BrandDTO brandDTO : brandDTOs ) {
            list.add( brandDTOToBrand( brandDTO ) );
        }

        return list;
    }
}
