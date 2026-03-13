package com.literally.backend.dtos;

import com.literally.backend.enums.EntityTypeEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MediaDTO {
    private UUID id;

    private UUID entityId;
    private EntityTypeEnum entityType;

    private byte[] data;
}
