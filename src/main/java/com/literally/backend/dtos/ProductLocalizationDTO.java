package com.literally.backend.dtos;

import com.literally.backend.entities.Product;
import com.literally.backend.enums.LanguageEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class ProductLocalizationDTO {
    private UUID id;

    private Product product;
    private LanguageEnum language;

    private String name;
    private String description;
}

