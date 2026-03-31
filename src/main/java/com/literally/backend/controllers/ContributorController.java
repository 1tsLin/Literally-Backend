package com.literally.backend.controllers;

import com.literally.backend.dtos.ContributorDTO;
import com.literally.backend.filters.ContributorFilter;
import com.literally.backend.services.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contributors")
@RequiredArgsConstructor
public class ContributorController {

    private final ContributorService contributorService;

    /*------------------------------------------------------------------------------------------------------------------
                                               Contributor  CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{contributorId}")
    public ContributorDTO getContributor(@PathVariable UUID contributorId) {
        return contributorService.getById(contributorId);
    }

    @GetMapping
    public List<ContributorDTO> getContributors(ContributorFilter filter) {
        return contributorService.getContributors(filter);
    }

    @PostMapping
    public ContributorDTO createContributor(@RequestBody ContributorDTO dto) {
        return contributorService.create(dto);
    }

    @PutMapping("/{contributorId}")
    public ContributorDTO updateContributor(@PathVariable UUID contributorId, @RequestBody ContributorDTO dto) {
        return contributorService.update(contributorId, dto);
    }

    @DeleteMapping("/{contributorId}")
    public void deleteContributor(@PathVariable UUID contributorId) {
        contributorService.delete(contributorId);
    }
}
