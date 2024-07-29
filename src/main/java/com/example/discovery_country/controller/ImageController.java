package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import com.example.discovery_country.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> createImage(@RequestBody ImageRequest imageRequest) {
        log.info("ActionLog.createImage start");

        ImageResponse imageResponse = imageService.createImage(imageRequest);

        log.info("ActionLog.createImage end");

        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageResponse> updateImage(@PathVariable Long id, @RequestBody ImageRequest imageRequest) {
        log.info("ActionLog.updateImage start with id#" + id);

        ImageResponse imageResponse = imageService.updateImage(id, imageRequest);

        log.info("ActionLog.updateImage end with id#" + id);

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        log.info("ActionLog.deleteImage start with id#" + id);

        imageService.deleteImage(id);

        log.info("ActionLog.deleteImage end with id#" + id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getImageById(@PathVariable Long id) {
        log.info("ActionLog.getImage start with id#" + id);

        ImageResponse imageResponse = imageService.getImageById(id);

        log.info("ActionLog.getImage end with id#" + id);

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponse>> getAllImages() {
        log.info("ActionLog.getAllImages start");

        List<ImageResponse> images = imageService.getAllImages();

        log.info("ActionLog.getAllImages end");

        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
