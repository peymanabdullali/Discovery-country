package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.exception.ImageNotFoundException;
import com.example.discovery_country.mapper.ImageMapper;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;


    public String addPhoto(long id, MultipartFile file) {
        String uploadDir = "C:\\photos\\";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        try {
            String fileName = file.getOriginalFilename();
            File destinationFile = new File(uploadDir + fileName);
            file.transferTo(destinationFile);

            ImageEntity imageEntity = imageRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("ImageEntity not found for id: " + id));

            imageEntity.setName(fileName);
            imageEntity.setUrl(uploadDir + fileName);

            // Save updated ImageEntity with file details
            imageRepository.save(imageEntity);

            return "File uploaded and saved successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to find ImageEntity!";
        }
    }


    public void deleteImage(Long id) {
        log.info("ActionLog.imageDelete start with id#" + id);

        imageRepository.softDelete(id);

        log.info("ActionLog.imageDelete end with id#" + id);
    }


}
