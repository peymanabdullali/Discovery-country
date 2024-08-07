package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.dao.repository.ScenicSpotRepository;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.mapper.ScenicSpotMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import com.example.discovery_country.model.dto.response.ScenicSpotResponseForFindById;
import com.example.discovery_country.service.specification.ScenicSpotSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenicSpotService {
    private final ScenicSpotRepository scenicSpotRepository;
    private final ImageRepository imageRepository;
    private final ScenicSpotMapper scenicSpotMapper;
    private final IncreaseViewCount viewCount;

    public ScenicSpotResponse createScenicSpot(ScenicSpotRequest request) {
        log.info("ActionLog.createScenicSpot start");
        ScenicSpotEntity scenicSpot = scenicSpotRepository.save(scenicSpotMapper.mapToEntity(request, imageRepository.findAllById(request.getImageIds())));
        ScenicSpotResponse scenicSpotResponse = scenicSpotMapper.mapToResponse(scenicSpot);
        log.info("ActionLog.createScenicSpot end");
        return scenicSpotResponse;

    }


    public Page<ScenicSpotResponse> getScenicSpots(CriteriaRequestForName criteriaRequest, Pageable pageable) {
        log.info("ActionLog.getScenicSpots start");

        Specification<ScenicSpotEntity> spec = ScenicSpotSpecification.getScenicSpotByCriteria(criteriaRequest);
        List<ScenicSpotResponse> scenicSpotResponse = scenicSpotMapper.mapToResponseList(scenicSpotRepository.findAll(spec, pageable).toList());
        log.info("ActionLog.getScenicSpots end");
        return new PageImpl<>(scenicSpotResponse);
    }

    public ScenicSpotResponseForFindById findById(Long id) {
        log.info("ActionLog.findScenicSpot start with id#" + id);
        ScenicSpotEntity scenicSpotEntity = scenicSpotRepository.findByIdAndCheckStatusTrueAndStatusFalse(id).orElseThrow(() -> new RuntimeException("SCENIC_SPOT_NOT_FOUND"));
       viewCount.updateViewCount(scenicSpotEntity);
        scenicSpotEntity.setReviews(scenicSpotEntity.getReviews().stream().filter(i -> !i.isStatus()).toList());
        scenicSpotEntity.setImages(scenicSpotEntity.getImages().stream().filter(i -> !i.getDeleted()).toList());
        ScenicSpotResponseForFindById scenicSpotResponseForFindById = scenicSpotMapper.mapToResponseForFindById(scenicSpotEntity);
        log.info("ActionLog.findScenicSpot end");
        return scenicSpotResponseForFindById;

    }

    public void updateStatus(Long id) {
        log.info("ActionLog.updateStatus start with id#" + id);
        scenicSpotRepository.updateStatus(id);
        log.info("ActionLog.updateStatus end");
    }

    public void softDelete(Long id) {
        log.info("ActionLog.softDelete start with id#" + id);
        scenicSpotRepository.softDelete(id);
        log.info("ActionLog.softDelete end");
    }
}


