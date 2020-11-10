package com.mvnikitin.eshop.services;

import com.mvnikitin.eshop.dto.BrandDTO;
import com.mvnikitin.eshop.mappers.BrandMapper;
import com.mvnikitin.eshop.model.Category;
import com.mvnikitin.eshop.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private BrandMapper brandMapper;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public BrandDTO findById(Integer id) {
        return brandMapper.brandToBrandDTO(
                brandRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Brand [id:" +
                                id + "] not found")));
    }

    @Override
    public List<BrandDTO> findAll() {
        Sort sort = Sort.sort(Category.class)
                .by("name").ascending();

        return brandMapper.brandsToBrandDTOs(
                brandRepository.findAll(sort));
    }

    @Override
    @Transactional
    public BrandDTO save(BrandDTO categoryDTO) {
        return brandMapper.brandToBrandDTO(
                brandRepository.save(
                        brandMapper.brandDTOToBrand(categoryDTO)));
    }

    @Override
    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }
}
