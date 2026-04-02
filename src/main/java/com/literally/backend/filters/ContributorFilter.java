package com.literally.backend.filters;

import com.literally.backend.enums.ContributorCategoryEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class ContributorFilter {
    private UUID id;
    private ContributorCategoryEnum category;
    private String name;
}
