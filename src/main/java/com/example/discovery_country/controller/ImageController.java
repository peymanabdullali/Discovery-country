package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import com.example.discovery_country.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/addPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageResponse> addPhoto(@RequestPart MultipartFile photo) {
       ImageResponse imageResponse=imageService.addPhoto(photo);
       return ResponseEntity.ok(imageResponse);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        log.info("ActionLog.deleteImage start with id#" + id);

        imageService.deleteImage(id);

        log.info("ActionLog.deleteImage end with id#" + id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
