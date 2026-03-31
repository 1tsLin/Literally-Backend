package com.literally.backend.mappers;

import com.literally.backend.dtos.SeriesLocalizationDTO;
import com.literally.backend.entities.SeriesLocalization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeriesLocalizationMapper {

    public SeriesLocalizationDTO mapToDto(SeriesLocalization entity){
        return SeriesLocalizationDTO.builder()
                .id(entity.getId())
                .seriesId(entity.getSeries().getId())
                .language(entity.getLanguage())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public SeriesLocalization mapToEntity(SeriesLocalizationDTO dto){
        return SeriesLocalization.builder()
                .language(dto.getLanguage())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
