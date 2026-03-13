package com.literally.backend.mappers;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.entities.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductMapper {

    private final SeriesMapper seriesMapper;
    private final ReviewMapper reviewMapper;

    public ProductDTO mapToDto(Product entity){
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

                .author(entity.getAuthor())
                .editor(entity.getEditor())
                .illustrator(entity.getIllustrator())

                .ean(entity.getEan())
                .pages(entity.getPages())
                .publishingDate(entity.getPublishingDate())

                .height(entity.getHeight())
                .width(entity.getWidth())
                .thickness(entity.getThickness())
                .weight(entity.getWeight())

                .reviews(entity.getReviews().stream()
                        .map(reviewMapper::mapToDto)
                        .collect(Collectors.toSet()))

                .isActive(entity.getIsActive())
                .build();
    }

    public Product mapToEntity(ProductDTO dto){
        return Product.builder()
                .id(dto.getId())

                .series(seriesMapper.mapToEntity(dto.getSeries()))
                .alias(new ArrayList<>(dto.getAlias()))
                .format(dto.getFormat())
                .audience(dto.getAudience())
                .genres(new ArrayList<>(dto.getGenres()))

                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .sales(dto.getSales())

                .author(dto.getAuthor())
                .editor(dto.getEditor())
                .illustrator(dto.getIllustrator())

                .ean(dto.getEan())
                .pages(dto.getPages())
                .publishingDate(dto.getPublishingDate())

                .height(dto.getHeight())
                .width(dto.getWidth())
                .thickness(dto.getThickness())
                .weight(dto.getWeight())

                .reviews(dto.getReviews().stream()
                        .map(reviewMapper::mapToEntity)
                        .collect(Collectors.toSet()))

                .isActive(dto.getIsActive())
                .build();
    }
}
