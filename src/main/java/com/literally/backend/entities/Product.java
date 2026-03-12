package com.literally.backend.entities;

import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    private List<String> alias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BookFormatEnum format;

    @Enumerated(EnumType.STRING)
    private BookAudienceEnum audience;

    @Enumerated(EnumType.STRING)
    private List<BookGenreEnum> genres = new ArrayList<>();

    @Column(precision = 5, scale = 2)
    private BigDecimal price;

    private Integer quantity;

    private Integer sales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Contributor author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private Contributor editor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "illustrator_id")
    private Contributor illustrator;

    private Integer ean;

    private Integer pages;

    private Date publishingDate;

    @Column(precision = 5, scale = 3)
    private BigDecimal height;

    @Column(precision = 5, scale = 3)
    private BigDecimal width;

    @Column(precision = 5, scale = 3)
    private BigDecimal thickness;

    @Column(precision = 5, scale = 3)
    private BigDecimal weight;

    private Boolean isActive;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new HashSet<>();

    /*
    @OneToMany(mappedBy = "product")
    Set<OrderItem> salesHistory;
    */

}
