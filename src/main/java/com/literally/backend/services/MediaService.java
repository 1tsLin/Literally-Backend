package com.literally.backend.services;

import com.literally.backend.dtos.MediaDTO;
import com.literally.backend.entities.Media;
import com.literally.backend.enums.MediaCategoryEnum;
import com.literally.backend.mappers.MediaMapper;
import com.literally.backend.repositories.MediaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /*------------------------------------------------------------------------------------------------------------------
                                                Media CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public List<MediaDTO> getByEntity(UUID entityId){
        List<Media> medias = mediaRepository.findByEntityId(entityId);
        return medias.stream().map(mediaMapper::mapToDto).collect(Collectors.toList());
    }

    public byte[] get(UUID mediaId){
        Media media = mediaRepository.findById(mediaId).orElseThrow();
        return media.getData();
    }

    @Transactional
    public MediaDTO create(MediaDTO dto, MultipartFile file) throws IOException {
        Media media = Media.builder()
                .entityId(dto.getEntityId())
                .category(dto.getCategory())
                .data(file.getBytes())
                .build();
        media = mediaRepository.save(media);
        return mediaMapper.mapToDto(media);
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

    @Transactional
    public MediaDTO update(UUID mediaId, MediaDTO dto, MultipartFile file) throws IOException {
        Media media = mediaRepository.findById(mediaId)
                        .orElseThrow(() -> new RuntimeException("Media not found: " + mediaId));
        mediaMapper.updateFromDto(dto, media);
        media.setData(file.getBytes());

        mediaRepository.save(media);

        return mediaMapper.mapToDto(media);
    }
}
