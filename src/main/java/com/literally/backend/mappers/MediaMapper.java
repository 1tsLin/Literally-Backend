package com.literally.backend.mappers;

import com.literally.backend.dtos.MediaDTO;
import com.literally.backend.entities.Media;

public class MediaMapper {

    public MediaDTO mapToDto(Media entity){
        return MediaDTO.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .entityType(entity.getEntityType())
                .data(entity.getData())
                .build();
    }

    public Media mapToEntity(MediaDTO dto){
        return Media.builder()
                .id(dto.getId())
                .entityId(dto.getEntityId())
                .entityType(dto.getEntityType())
                .data(dto.getData())
                .build();
    }
}
