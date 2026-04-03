package com.literally.backend.entities;

import com.literally.backend.enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product_localizations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    private Boolean isActive;
}
