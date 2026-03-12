package com.literally.backend.entities;

import com.literally.backend.enums.ContributorCategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contributors")
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ContributorCategoryEnum category;

    private String name;

}
