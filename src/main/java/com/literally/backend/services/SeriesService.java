package com.literally.backend.services;

import com.literally.backend.dtos.ContributorDTO;
import com.literally.backend.dtos.SeriesDTO;
import com.literally.backend.dtos.SeriesLocalizationDTO;
import com.literally.backend.entities.Contributor;
import com.literally.backend.entities.Series;
import com.literally.backend.entities.SeriesLocalization;
import com.literally.backend.filters.ContributorFilter;
import com.literally.backend.filters.SeriesFilter;
import com.literally.backend.mappers.SeriesLocalizationMapper;
import com.literally.backend.mappers.SeriesMapper;
import com.literally.backend.repositories.SeriesLocalizationRepository;
import com.literally.backend.repositories.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    private final SeriesLocalizationRepository seriesLocalizationRepository;
    private final SeriesLocalizationMapper seriesLocalizationMapper;

    /*------------------------------------------------------------------------------------------------------------------
                                               Series CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public SeriesDTO getById(UUID seriesId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found : " + seriesId));
        return seriesMapper.mapToDto(series);
    }

    @Transactional
    public SeriesDTO create(SeriesDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Series create dto is null");

        Series series = seriesRepository.save(seriesMapper.mapToEntity(dto));

        return seriesMapper.mapToDto(series);
    }

    @Transactional
    public SeriesDTO update(UUID seriesId, SeriesDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Series update dto is null");

        if (!seriesRepository.existsById(seriesId))
            throw new RuntimeException("Series not found : " + seriesId);

        Series series = seriesMapper.mapToEntity(dto);
        series.setId(seriesId);

        seriesRepository.save(series);

        return seriesMapper.mapToDto(series);
    }

    @Transactional
    public void delete(UUID seriesId) {
        if (!seriesRepository.existsById(seriesId))
            throw new RuntimeException("Series not found : " + seriesId);

        seriesRepository.deleteById(seriesId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                            Series localizations CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/
    public List<SeriesLocalizationDTO> getSeriesLocalizations(SeriesFilter filter) {

        Specification<SeriesLocalization> spec = Specification.allOf();

        if (filter.getId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("id"), filter.getId()));
        }

        if (filter.getSeriesId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("series").get("id"), filter.getSeriesId()));
        }

        if (filter.getLanguage() != null){
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("language"), filter.getLanguage()));
        }

        if (filter.getName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")),
                            "%" + filter.getName().toLowerCase() + "%"));
        }

        return seriesLocalizationRepository.findAll(spec).stream()
                .map(seriesLocalizationMapper::mapToDto)
                .toList();
    }

}
