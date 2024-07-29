package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.dao.repository.ReviewRepository;
import com.example.discovery_country.dao.repository.ZoneRepository;
import com.example.discovery_country.mapper.ReviewMapper;
import com.example.discovery_country.mapper.ZoneMapper;
import com.example.discovery_country.model.dto.request.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ReviewRequest;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.ZoneResponse;
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
public class ReviewService {
//    private final ReviewRepository reviewRepository;
//    private final ReviewMapper reviewMapper;
//
//    public ZoneResponse create(ReviewRequest request) {
//        log.info("ActionLog.createZone start");
//
//        ZoneEntity zoneEntity = zoneMapper.mapToEntity(request, regionRepository.findAllById(request.getRegionIds()));
//        ZoneEntity zone = zoneRepository.save(zoneEntity);
//        ZoneResponse zoneResponse = zoneMapper.mapToResponse(zone);
//        log.info("ActionLog.createZone end");
//        return zoneResponse;
//
//    }
//
//    public Page<ZoneResponse> getZones(CriteriaRequestForName criteriaRequest, Pageable pageable) {
//        log.info("ActionLog.getZone start");
//
//        Specification<ZoneEntity> spec = ZoneSpecification.getZoneByCriteria(criteriaRequest);
//        Page<ZoneEntity> zoneEntities = zoneRepository.findAll(spec, pageable);
//        List<ZoneResponse> zoneResponses = zoneMapper.mapToZoneResponseList(zoneEntities.toList());
//        log.info("ActionLog.getZone end");
//
//        return new PageImpl<>(zoneResponses);
//
//    }
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
//    public void delete(Long id) {
//        log.info("ActionLog.deleteZone start with id#" + id);
//        zoneRepository.deleteById(id) ;
//        log.info("ActionLog.updateZone end");
//    }
//

}
