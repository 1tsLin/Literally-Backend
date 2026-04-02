package com.literally.backend.mappers;

import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.entities.ProductLocalization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductLocalizationMapper {

    public ProductLocalizationDTO mapToDto(ProductLocalization entity) {
        return ProductLocalizationDTO.builder()
                .id(entity.getId())
                .language(entity.getLanguage())
                .name(entity.getName())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .build();
    }

    public ProductLocalization mapToEntity(ProductLocalizationDTO dto) {
        return ProductLocalization.builder()
                .language(dto.getLanguage())
                .name(dto.getName())
                .description(dto.getDescription())
                .isActive(dto.getIsActive())
                .build();
    }

    public void updateFromDto(ProductLocalizationDTO dto, ProductLocalization entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setIsActive(dto.getIsActive());
    }
}
