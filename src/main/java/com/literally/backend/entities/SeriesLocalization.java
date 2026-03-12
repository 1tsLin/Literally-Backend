package com.literally.backend.entities;

import com.literally.backend.enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "series_localizations")
public class SeriesLocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "series_id")
    private Series series;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    private String name;

    private String description;

}
