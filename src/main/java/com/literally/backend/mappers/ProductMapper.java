package com.literally.backend.mappers;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final SeriesMapper seriesMapper;
    private final ContributorMapper contributorMapper;

    public ProductDTO mapToDto(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())

                .series(seriesMapper.mapToDto(entity.getSeries()))
                .alias(new ArrayList<>(entity.getAlias()))
                .format(entity.getFormat())
                .audience(entity.getAudience())
                .genres(new ArrayList<>(entity.getGenres()))

                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .sales(entity.getSales())

                .author(contributorMapper.mapToDto(entity.getAuthor()))
                .editor(contributorMapper.mapToDto(entity.getEditor()))
                .illustrator(contributorMapper.mapToDto(entity.getIllustrator()))

                .ean(entity.getEan())
                .pages(entity.getPages())
                .publishingDate(entity.getPublishingDate())

                .height(entity.getHeight())
                .width(entity.getWidth())
                .thickness(entity.getThickness())
                .weight(entity.getWeight())

                .reviewIds(entity.getReviews().stream()
                        .map(Review::getId)
                        .collect(Collectors.toSet()))

                .build();
    }

    public Product mapToEntity(ProductDTO dto) {
        return Product.builder()
                .series(seriesMapper.mapToEntity(dto.getSeries()))
                .alias(new ArrayList<>(dto.getAlias()))
                .format(dto.getFormat())
                .audience(dto.getAudience())
                .genres(new ArrayList<>(dto.getGenres()))

                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .sales(dto.getSales())

                .author(contributorMapper.mapToEntity(dto.getAuthor()))
                .editor(contributorMapper.mapToEntity(dto.getEditor()))
                .illustrator(contributorMapper.mapToEntity(dto.getIllustrator()))

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
