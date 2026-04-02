package com.literally.backend.services;

import com.literally.backend.dtos.ContributorDTO;
import com.literally.backend.entities.Contributor;
import com.literally.backend.enums.ContributorCategoryEnum;
import com.literally.backend.filters.ContributorFilter;
import com.literally.backend.mappers.ContributorMapper;
import com.literally.backend.repositories.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContributorService {

    private final ContributorRepository contributorRepository;
    private final ContributorMapper contributorMapper;

    /*------------------------------------------------------------------------------------------------------------------
                                               Contributor CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public ContributorDTO getById(UUID contributorId) {
        Contributor contributor = contributorRepository.findById(contributorId)
                .orElseThrow(() -> new RuntimeException("Contributor not found : " + contributorId));

        return contributorMapper.mapToDto(contributor);
    }

    public Contributor getByIdAndCategory(UUID contributorId, ContributorCategoryEnum category){
        return contributorRepository.findByIdAndCategory(contributorId, category)
                .orElseThrow(() -> new RuntimeException("Contributor not found : " + contributorId));
    }

    public List<ContributorDTO> getContributors(ContributorFilter filter) {

        Specification<Contributor> spec = Specification.allOf();

        if (filter.getId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("id"), filter.getId()));
        }

        if (filter.getCategory() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category"), filter.getCategory()));
        }

        if (filter.getName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")),
                            "%" + filter.getName().toLowerCase() + "%"));
        }

        return contributorRepository.findAll(spec).stream()
                .map(contributorMapper::mapToDto)
                .toList();
    }

    @Transactional
    public ContributorDTO create(ContributorDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Contributor create dto is null");

        Contributor contributor = contributorRepository.save(contributorMapper.mapToEntity(dto));

        return contributorMapper.mapToDto(contributor);
    }

    @Transactional
    public ContributorDTO update(UUID contributorId, ContributorDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Contributor update dto is null");

        if (!contributorRepository.existsById(contributorId))
            throw new RuntimeException("Contributor not found : " + contributorId);

        Contributor contributor = contributorMapper.mapToEntity(dto);
        dto.setId(contributorId);

        contributorRepository.save(contributor);

        return contributorMapper.mapToDto(contributor);
    }

    @Transactional
    public void delete(UUID contributorId) {
        if (!contributorRepository.existsById(contributorId))
            throw new RuntimeException("Contributor not found : " + contributorId);

        contributorRepository.deleteById(contributorId);
    }

}
