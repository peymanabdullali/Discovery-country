package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.exception.ImageNotFoundException;
import com.example.discovery_country.mapper.ImageMapper;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageResponse createImage(ImageRequest imageRequest) {
        log.info("ActionLog.imageCreate start");

        ImageEntity imageEntity = imageMapper.mapToEntity(imageRequest);
        ImageEntity savedImage = imageRepository.save(imageEntity);

        log.info("ActionLog.imageCreate end");
        return imageMapper.mapToResponse(savedImage);
    }

    public ImageResponse updateImage(Long id, ImageRequest imageRequest) {
        log.info("ActionLog.imageUpdate start with id#" + id);

        ImageEntity imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(HttpStatus.NOT_FOUND.name(),"Image not found"));
        imageMapper.mapForUpdate(imageEntity, imageRequest);
        ImageEntity updatedImage = imageRepository.save(imageEntity);

        log.info("ActionLog.imageUpdate end");
        return imageMapper.mapToResponse(updatedImage);
    }

    public void deleteImage(Long id) {
        log.info("ActionLog.imageDelete start with id#" + id);

        imageRepository.softDelete(id);

        log.info("ActionLog.imageDelete end with id#" + id);
    }

    public ImageResponse getImageById(Long id) {
        log.info("ActionLog.getImage start with id#" + id);

        ImageEntity imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(HttpStatus.NOT_FOUND.name(),"Image not found"));
        log.info("ActionLog.getImage end with id#" + id);

        return imageMapper.mapToResponse(imageEntity);
    }

    public List<ImageResponse> getAllImages() {
        log.info("ActionLog.getAllImages start");

        List<ImageResponse> images = imageRepository.findAll().stream()
                .map(imageMapper::mapToResponse)
                .collect(Collectors.toList());

        log.info("ActionLog.getAllImages end");
        return images;
    }
}
