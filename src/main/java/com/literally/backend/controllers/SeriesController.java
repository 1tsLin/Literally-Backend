package com.literally.backend.controllers;

import com.literally.backend.dtos.SeriesDTO;
import com.literally.backend.services.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    /*------------------------------------------------------------------------------------------------------------------
                                                  Series CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{seriesId}")
    public SeriesDTO getSeries(@PathVariable UUID seriesId) {
        return seriesService.getById(seriesId);
    }

    @PostMapping
    public SeriesDTO createSeries(@RequestBody SeriesDTO dto) {
        return seriesService.create(dto);
    }

    @PutMapping("/{seriesId}")
    public SeriesDTO updateSeries(@PathVariable UUID seriesId, @RequestBody SeriesDTO dto) {
        return seriesService.update(seriesId, dto);
    }

    @DeleteMapping("/{seriesId}")
    public void deleteSeries(@PathVariable UUID seriesId) {
        seriesService.delete(seriesId);
    }
}
