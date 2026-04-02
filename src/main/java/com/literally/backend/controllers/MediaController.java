package com.literally.backend.controllers;

import com.literally.backend.dtos.MediaDTO;
import com.literally.backend.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/{mediaId}")
    public ResponseEntity<byte[]> getMedia(@PathVariable UUID mediaId) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(mediaService.get(mediaId));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaDTO> createMedia(@RequestPart("dto") MediaDTO dto,
                                @RequestPart("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(mediaService.create(dto, file));
    }

    @PutMapping(path = "/{mediaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable UUID mediaId,
                                @RequestPart("dto") MediaDTO dto,
                                @RequestPart("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(mediaService.update(mediaId, dto, file));
    }
}
