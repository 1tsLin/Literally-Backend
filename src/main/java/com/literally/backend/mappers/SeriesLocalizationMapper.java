package com.literally.backend.mappers;

import com.literally.backend.dtos.SeriesLocalizationDTO;
import com.literally.backend.entities.SeriesLocalization;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeriesLocalizationMapper {

    private final SeriesMapper seriesMapper;

    public SeriesLocalizationDTO mapToDto(SeriesLocalization entity){
        return SeriesLocalizationDTO.builder()
                .id(entity.getId())
                .series(seriesMapper.mapToDto(entity.getSeries()))
                .language(entity.getLanguage())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public SeriesLocalization mapToDto(SeriesLocalizationDTO dto){
        return SeriesLocalization.builder()
                .id(dto.getId())
                .series(seriesMapper.mapToEntity(dto.getSeries()))
                .language(dto.getLanguage())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
