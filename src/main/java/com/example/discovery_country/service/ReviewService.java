package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.repository.ReviewRepository;
import com.example.discovery_country.mapper.ReviewMapper;
import com.example.discovery_country.model.dto.request.ReviewRequestForHomeHotel;
import com.example.discovery_country.model.dto.request.ReviewRequestForRestaurant;
import com.example.discovery_country.model.dto.request.ReviewRequestForScenicSpots;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewResponse createRestaurantReview(ReviewRequestForRestaurant request) {
        log.info("ActionLog.createRestaurantReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createRestaurantReview end");
        return reviewResponse;
    }
    public ReviewResponse createScenicSpotReview(ReviewRequestForScenicSpots request) {
        log.info("ActionLog.createScenicSpotReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createScenicSpotReview end");
        return reviewResponse;

    }    public ReviewResponse createHomeHotelReview(ReviewRequestForHomeHotel request) {
        log.info("ActionLog.createHomeHotelReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createHomeHotelReview end");
        return reviewResponse;

    }

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
