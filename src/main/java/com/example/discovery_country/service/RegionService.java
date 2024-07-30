package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.mapper.RegionMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.*;
import com.example.discovery_country.model.dto.response.RegionResponse;
import com.example.discovery_country.service.specification.RegionSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionResponse createRegion(RegionRequest request) {
        log.info("ActionLog.createRegion start");
        RegionEntity region = regionRepository.save(regionMapper.mapToEntity(request));
        RegionResponse regionResponse = regionMapper.mapToResponse(region);
        log.info("ActionLog.createRegion end");
        return regionResponse;

    }

    public Page<RegionResponse> getRegions(CriteriaRequestForName criteriaRequest, Pageable pageable) {
        log.info("ActionLog.getRegions start");

        Specification<RegionEntity> spec = RegionSpecification.getRegionByCriteria(criteriaRequest);
        Page<RegionEntity> regionEntities = regionRepository.findAll(spec, pageable);
        List<RegionResponse> regionResponses = regionMapper.mapEntityListToResponseList(regionEntities.toList());
        log.info("ActionLog.getRegions end");

        return new PageImpl<>(regionResponses);

    }
//
//    public ZoneResponse update(Long id, ZoneRequest zoneRequest) {
//
//        log.info("ActionLog.updateZone start with id#" + id);
//
//        ZoneEntity zoneEntity = zoneRepository.findById(id).orElseThrow(() -> new RuntimeException(HttpStatus.NOT_FOUND.name()));
//        zoneMapper.mapForUpdate(zoneEntity, zoneRequest);
//        zoneEntity = zoneRepository.save(zoneEntity);
//        ZoneResponse zoneResponse = zoneMapper.mapToResponse(zoneEntity);
//        log.info("ActionLog.updateZone end");
//        return zoneResponse;
//    }
//
    public void deleteRegion(Long id) {
        log.info("ActionLog.deleteRegion start with id#" + id);
        regionRepository.deleteById(id) ;
        log.info("ActionLog.deleteRegion end");
    }


}
