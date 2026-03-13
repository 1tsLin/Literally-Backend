package com.literally.backend.mappers;

import com.literally.backend.dtos.ContributorDTO;
import com.literally.backend.entities.Contributor;
import org.springframework.stereotype.Component;

@Component
public class ContributorMapper {

    public ContributorDTO mapToDto(Contributor entity){
        return ContributorDTO.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .name(entity.getName())
                .build();
    }

    public Contributor mapToEntity(ContributorDTO dto){
        return Contributor.builder()
                .category(dto.getCategory())
                .name(dto.getName())
                .build();
    }
}
