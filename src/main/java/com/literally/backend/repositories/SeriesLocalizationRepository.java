package com.literally.backend.repositories;

import com.literally.backend.entities.SeriesLocalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeriesLocalizationRepository extends JpaRepository<SeriesLocalization, UUID>,
        JpaSpecificationExecutor<SeriesLocalization> {
}
