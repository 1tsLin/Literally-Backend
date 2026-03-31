package com.literally.backend.dtos;

import com.literally.backend.enums.MediaCategoryEnum;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {
    private UUID id;

    private UUID entityId;
    private MediaCategoryEnum category;

    private byte[] data;
}
