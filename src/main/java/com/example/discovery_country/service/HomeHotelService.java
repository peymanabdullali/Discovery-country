package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.enums.Status;
import com.example.discovery_country.exception.ActivityNotFoundException;
import com.example.discovery_country.exception.HomeHotelNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.helper.RatingHelper;
import com.example.discovery_country.helper.UpdateLike;
import com.example.discovery_country.mapper.HomeHotelMapper;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.HomeHotelResponse;
import com.example.discovery_country.model.dto.response.HomeHotelResponseFindById;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeHotelService {
    private final HomeHotelMapper homeHotelMapper;
    private final HomeHotelRepository homeHotelRepository;
    private final ImageRepository imageRepository;
    private final RegionRepository regionRepository;
    private final IncreaseViewCount viewCount;
    private final RatingHelper ratingHelper;
    private final UpdateLike updateLike;

    public HomeHotelResponse create(HomeHotelRequest homeHotelRequest){

        log.info("ActionLog.createHomeHotel start");

        HomeHotelEntity homeHotelEntity=homeHotelMapper.mapToEntity(homeHotelRequest, imageRepository.findAllById(homeHotelRequest.getImageIds()));
        HomeHotelResponse homeHotelResponse=homeHotelMapper.mapToResponse(homeHotelRepository.save(homeHotelEntity));

        log.info("ActionLog.createHomeHotel end");

        return homeHotelResponse;

    }


    public HomeHotelResponseFindById homeHotelResponseFindById(Long id) {

        log.info("ActionLog.homeHotelResponseFindById start with id#" + id);

        HomeHotelEntity homeHotelEntity = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));
        viewCount.updateViewCount(homeHotelEntity);

        log.info("ActionLog.homeHotelResponseFindById end");

        return homeHotelMapper.mapToHomeHotelResponseFindById(homeHotelEntity);
    }

    public HomeHotelResponse getHomeHotel(Long id) {

        log.info("ActionLog.getHomeHotel start with id#" + id);

        HomeHotelEntity homeHotel = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));
        homeHotel.setViewed(homeHotel.getViewed() + 1);
        homeHotelRepository.save(homeHotel);

        log.info("ActionLog.getHomeHotel end with id#" + id);

        return homeHotelMapper.mapToResponse(homeHotel);
    }
    public HomeHotelResponse updateHomeHotel(Long id, HomeHotelRequest homeHotelRequest) {
        log.info("ActionLog.updateHomeHotel start with id#" + id);

        HomeHotelEntity homeHotelEntity = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));
        homeHotelMapper.mapForUpdate(homeHotelEntity, homeHotelRequest);
        homeHotelEntity = homeHotelRepository.save(homeHotelEntity);

        log.info("ActionLog.updateHomeHotel end");

        return homeHotelMapper.mapToResponse(homeHotelEntity);
    }
    public void softDelete(Long id) {
        log.info("ActionLog.softDelete start with id#" + id);
        homeHotelRepository.softDelete(id);
        log.info("ActionLog.softDelete end");
    }

    public void rateHomeHotel(Long id, int stars) {
        HomeHotelEntity homeHotel = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));

        ratingHelper.addRating(homeHotel, stars);
    }

    public void updateLikeCount(Long id, boolean increment) {
        HomeHotelEntity homeHotelEntity = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));

        updateLike.updateLikeCount(homeHotelEntity, increment);
    }

}
