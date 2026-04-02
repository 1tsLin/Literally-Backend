package com.literally.backend.entities;

import com.literally.backend.enums.MediaCategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "medias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID entityId;

    @Enumerated(EnumType.STRING)
    private MediaCategoryEnum category;

    @Column(name = "data", columnDefinition = "bytea")
    private byte[] data;

}
