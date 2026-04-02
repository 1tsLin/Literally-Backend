package com.literally.backend.dtos;

import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID id;

    private SeriesDTO series;
    private List<String> alias = new ArrayList<>();
    private BookFormatEnum format;
    private BookAudienceEnum audience;
    private List<BookGenreEnum> genres = new ArrayList<>();

    private BigDecimal price;
    private Integer quantity;
    private Integer sales;

    private UUID authorId;
    private UUID editorId;
    private UUID illustratorId;

    private Integer ean;
    private Integer pages;
    private Date publishingDate;

    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal thickness;
    private BigDecimal weight;

    private Set<UUID> reviewIds;

    private List<ProductLocalizationDTO> localizations;
    private List<MediaDTO> medias;
}
