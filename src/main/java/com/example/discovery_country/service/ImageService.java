package com.example.discovery_country.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.exception.ImageNotFoundException;
import com.example.discovery_country.mapper.ImageMapper;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper mapper;
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    public List<Long> addPhoto(MultipartFile[] file) {
        List<Long> list = new ArrayList<>();
        for (MultipartFile f : file) {
            String filePath = uploadFile(f);

            ImageEntity imageEntity = mapper.mapToEntity("image", filePath);
            ImageEntity save = imageRepository.save(imageEntity);
            list.add(save.getId());
        }
        return list;
    }

    public void deleteImage(Long id) {
        log.info("ActionLog.imageDelete start with id#" + id);

        imageRepository.softDelete(id);

        log.info("ActionLog.imageDelete end with id#" + id);
    }

    public String uploadFile(MultipartFile multipartFile) {
        String filepath;
        String fileUrl;
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            filepath = "images/" + multipartFile.getOriginalFilename();
            s3Client.putObject(bucketName, filepath,
                    multipartFile.getInputStream(), objectMetadata);
            s3Client.setObjectAcl(bucketName, filepath, CannedAccessControlList.PublicRead);
            fileUrl = s3Client.getUrl(bucketName, filepath).toString();
        } catch (IOException e) {
            log.error("Error occurred ==> {}", e.getMessage());
            throw new RuntimeException("Error occurred in file upload ==> " + e.getMessage());
        }
        return fileUrl;
    }
}
