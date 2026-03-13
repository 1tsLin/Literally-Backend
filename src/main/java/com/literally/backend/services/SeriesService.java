package com.literally.backend.services;

import com.literally.backend.dtos.SeriesDTO;
import com.literally.backend.entities.Series;
import com.literally.backend.mappers.SeriesMapper;
import com.literally.backend.repositories.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

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
}
