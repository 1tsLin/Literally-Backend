package com.literally.backend.mappers;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.Review;
import com.literally.backend.enums.ContributorCategoryEnum;
import com.literally.backend.services.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final SeriesMapper seriesMapper;
    private final ContributorMapper contributorMapper;
    private final ContributorService contributorService;

    public ProductDTO mapToDto(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())

                .series(entity.getSeries() != null ? seriesMapper.mapToDto(entity.getSeries()) : null)
                .alias(new ArrayList<>(entity.getAlias()))
                .format(entity.getFormat())
                .audience(entity.getAudience())
                .genres(new ArrayList<>(entity.getGenres()))

                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .sales(entity.getSales())

                .authorId(entity.getAuthor().getId())
                .editorId(entity.getEditor().getId())
                .illustratorId(entity.getIllustrator().getId())

                .ean(entity.getEan())
                .pages(entity.getPages())
                .publishingDate(entity.getPublishingDate())

                .height(entity.getHeight())
                .width(entity.getWidth())
                .thickness(entity.getThickness())
                .weight(entity.getWeight())

                .reviewIds(entity.getReviews() != null
                        ? entity.getReviews().stream()
                        .map(Review::getId)
                        .collect(Collectors.toSet())
                        : new HashSet<>())

                .build();
    }

    public Product mapToEntity(ProductDTO dto) {
        return Product.builder()
                .series(dto.getSeries() != null ? seriesMapper.mapToEntity(dto.getSeries()) : null)
                .alias(new ArrayList<>(dto.getAlias()))
                .format(dto.getFormat())
                .audience(dto.getAudience())
                .genres(new ArrayList<>(dto.getGenres()))

                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .sales(dto.getSales())

                .author(contributorService.getByIdAndCategory(dto.getAuthorId(), ContributorCategoryEnum.AUTHOR))
                .editor(contributorService.getByIdAndCategory(dto.getEditorId(), ContributorCategoryEnum.EDITOR))
                .illustrator(contributorService.getByIdAndCategory(dto.getIllustratorId(), ContributorCategoryEnum.ILLUSTRATOR))

                .ean(dto.getEan())
                .pages(dto.getPages())
                .publishingDate(dto.getPublishingDate())

                .height(dto.getHeight())
                .width(dto.getWidth())
                .thickness(dto.getThickness())
                .weight(dto.getWeight())

                .build();
    }
}
