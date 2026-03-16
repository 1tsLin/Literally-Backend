package com.literally.backend.mappers;

import com.literally.backend.dtos.SeriesDTO;
import com.literally.backend.entities.Series;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SeriesMapper {

    public SeriesDTO mapToDto(Series entity){
        return SeriesDTO.builder()
                .id(entity.getId())

                .alias(new ArrayList<>(entity.getAlias()))
                .format(entity.getFormat())
                .audience(entity.getAudience())
                .genres(new ArrayList<>(entity.getGenres()))

                .author(entity.getAuthor())
                .editor(entity.getAuthor())
                .illustrator(entity.getIllustrator())

                .build();
    }

    public Series mapToEntity(SeriesDTO dto){
        return Series.builder()
                .alias(new ArrayList<>(dto.getAlias()))
                .format(dto.getFormat())
                .audience(dto.getAudience())
                .genres(new ArrayList<>(dto.getGenres()))

                .author(dto.getAuthor())
                .editor(dto.getAuthor())
                .illustrator(dto.getIllustrator())

                .build();
    }
}
