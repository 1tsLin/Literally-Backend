package com.literally.backend.mappers;

import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.entities.ProductLocalization;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductLocalizationMapper {

    private final ProductMapper productMapper;

    public ProductLocalizationDTO mapToDto(ProductLocalization entity){
        return ProductLocalizationDTO.builder()
                .id(entity.getId())
                .product(productMapper.mapToDto(entity.getProduct()))
                .language(entity.getLanguage())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public ProductLocalization mapToEntity(ProductLocalizationDTO dto){
        return ProductLocalization.builder()
                .id(dto.getId())
                .product(productMapper.mapToEntity(dto.getProduct()))
                .language(dto.getLanguage())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
