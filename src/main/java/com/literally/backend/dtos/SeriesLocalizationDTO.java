package com.literally.backend.dtos;

import com.literally.backend.enums.LanguageEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SeriesLocalizationDTO {
    private UUID id;
    private SeriesDTO series;
    private LanguageEnum language;
    private String name;
    private String description;
}
