package com.literally.backend.filters;

import com.literally.backend.enums.LanguageEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class SeriesFilter {
    private UUID id;
    private UUID seriesId;
    private LanguageEnum language;
    private String name;
}
