package com.literally.backend.repositories;

import com.literally.backend.entities.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, UUID> {
}
