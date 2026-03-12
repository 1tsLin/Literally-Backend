package com.literally.backend.entities;

import com.literally.backend.enums.EntityTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID entityId;

    @Enumerated(EnumType.STRING)
    private EntityTypeEnum entityType;

    @Lob
    private byte[] data;

}
