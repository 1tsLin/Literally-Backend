package com.literally.backend.services;

import com.literally.backend.entities.Media;
import com.literally.backend.enums.MediaCategoryEnum;
import com.literally.backend.repositories.MediaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;

    public byte[] get(UUID mediaId){
        Media media = mediaRepository.findById(mediaId).orElseThrow();
        return media.getData();
    }

    @Transactional
    public void create(MultipartFile file, UUID entityId, MediaCategoryEnum category) throws IOException {
        Media media = Media.builder()
                .entityId(entityId)
                .category(category)
                .data(file.getBytes())
                .build();
        mediaRepository.save(media);
    }
}
