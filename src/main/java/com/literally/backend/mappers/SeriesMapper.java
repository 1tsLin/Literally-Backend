package com.literally.backend.mappers;

import com.literally.backend.dtos.SeriesDTO;
import com.literally.backend.entities.Series;
import com.literally.backend.enums.ContributorCategoryEnum;
import com.literally.backend.services.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SeriesMapper {

    private final ContributorService contributorService;
    private final ContributorMapper contributorMapper;

    public SeriesDTO mapToDto(Series entity){
        return SeriesDTO.builder()
                .id(entity.getId())

                .alias(new ArrayList<>(entity.getAlias()))
                .format(entity.getFormat())
                .audience(entity.getAudience())
                .genres(new ArrayList<>(entity.getGenres()))

                .authorId(entity.getAuthor().getId())
                .editorId(entity.getAuthor().getId())
                .illustratorId(entity.getIllustrator().getId())

                .build();
    }

    public Series mapToEntity(SeriesDTO dto){
        return Series.builder()
                .alias(new ArrayList<>(dto.getAlias()))
                .format(dto.getFormat())
                .audience(dto.getAudience())
                .genres(new ArrayList<>(dto.getGenres()))

                .author(contributorMapper.mapToEntity(
                        contributorService.getByIdAndCategory(dto.getAuthorId(), ContributorCategoryEnum.AUTHOR)))
                .editor(contributorMapper.mapToEntity(
                        contributorService.getByIdAndCategory(dto.getEditorId(), ContributorCategoryEnum.EDITOR)))
                .illustrator(contributorMapper.mapToEntity(
                        contributorService.getByIdAndCategory(dto.getIllustratorId(), ContributorCategoryEnum.ILLUSTRATOR)))

                .build();
    }
}
