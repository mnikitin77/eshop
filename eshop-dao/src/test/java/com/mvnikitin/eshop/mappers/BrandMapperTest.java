package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.model.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandMapperTest {

    @Autowired
    private BrandMapper mapper;

    @Test
    public void whenBrandToBrandDTO_thenItsOk() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("name");

        BrandDTO brandDTO = mapper.brandToBrandDTO(brand);

        assertThat(brandDTO).isEqualToComparingFieldByField(brand);
    }

    @Test
    public void whenBrandDTOToBrand_thenItsOk() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1);
        brandDTO.setName("name");

        Brand brand = mapper.brandDTOToBrand(brandDTO);

        assertThat(brand).isEqualToComparingFieldByField(brandDTO);
    }

    @Test
    public void whenBrandsToBrandDTOs_thenItsOk() {
        List<Brand> brands = new ArrayList<>();

        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("name1");
        brands.add(brand);

        brand = new Brand();
        brand.setId(2);
        brand.setName("name2");
        brands.add(brand);

        List<BrandDTO> brandDTOs = mapper.brandsToBrandDTOs(brands);

        for (int i = 0; i < brands.size(); i++) {
            assertThat(brands.get(i))
                    .isEqualToComparingFieldByField(brandDTOs.get(i));
        }
    }

    @Test
    public void whenBrandDTOsToBrands_thenItsOk() {
        List<BrandDTO> brandDTOs = new ArrayList<>();

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1);
        brandDTO.setName("name1");
        brandDTOs.add(brandDTO);

        brandDTO = new BrandDTO();
        brandDTO.setId(2);
        brandDTO.setName("name2");
        brandDTOs.add(brandDTO);

        List<Brand> brands = mapper.brandDTOsToBrands(brandDTOs);

        for (int i = 0; i < brandDTOs.size(); i++) {
            assertThat(brandDTOs.get(i))
                    .isEqualToComparingFieldByField(brands.get(i));
        }
    }
}
