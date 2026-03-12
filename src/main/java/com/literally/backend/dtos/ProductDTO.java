package com.literally.backend.dtos;

import com.literally.backend.entities.Contributor;
import com.literally.backend.entities.Series;
import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
public class ProductDTO {
    private UUID id;

    private Series series;
    private List<String> alias = new ArrayList<>();
    private BookFormatEnum format;
    private BookAudienceEnum audience;
    private List<BookGenreEnum> genres = new ArrayList<>();

    private BigDecimal price;
    private Integer quantity;
    private Integer sales;

    private Contributor author;
    private Contributor editor;
    private Contributor illustrator;

    private Integer ean;
    private Integer pages;
    private Date publishingDate;

    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal thickness;
    private BigDecimal weight;

    private Set<ReviewDTO> reviews;

    private Boolean isActive;
}
