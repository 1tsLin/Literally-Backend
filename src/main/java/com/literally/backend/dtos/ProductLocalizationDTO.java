package com.literally.backend.dtos;

import com.literally.backend.enums.LanguageEnum;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLocalizationDTO {
    private UUID id;

    private LanguageEnum language;

    private String name;
    private String description;

    private Boolean isActive;
}

