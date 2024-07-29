package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.dao.repository.ScenicSpotRepository;
import com.example.discovery_country.dao.repository.ZoneRepository;
import com.example.discovery_country.mapper.ScenicSpotMapper;
import com.example.discovery_country.mapper.ZoneMapper;
import com.example.discovery_country.model.dto.request.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.request.ZoneCriteriaRequest;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.specification.ScenicSpotSpecification;
import com.example.discovery_country.service.specification.ZoneSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenicSpotService {
    private final ScenicSpotRepository scenicSpotRepository;
    private final ImageRepository imageRepository;
    private final ScenicSpotMapper scenicSpotMapper;

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

    public void updateStatus(Long id) {
        log.info("ActionLog.updateStatus start with id#" + id);
        scenicSpotRepository.updateStatus(id);
        log.info("ActionLog.updateStatus end");

    }
//
//    public void delete(Long id) {
//        log.info("ActionLog.deleteZone start with id#" + id);
//        zoneRepository.deleteById(id) ;
//        log.info("ActionLog.updateZone end");
//    }


}
