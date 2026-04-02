package com.literally.backend.filters;

import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductCatalogFilter {
    private String title;
    private BigDecimal price;
    private BigDecimal grade;
    private Boolean isFavorite;

    private UUID seriesId;

    private UUID authorId;
    private UUID illustratorId;
    private UUID editorID;

    private BookFormatEnum[] formats;
    private BookAudienceEnum[] audiences;
    private BookGenreEnum[] genres;
}
