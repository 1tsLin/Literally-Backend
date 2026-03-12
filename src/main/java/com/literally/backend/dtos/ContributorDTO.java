package com.literally.backend.dtos;

import com.literally.backend.enums.ContributorCategoryEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class ContributorDTO {
    private UUID id;
    private ContributorCategoryEnum category;
    private String name;
}
