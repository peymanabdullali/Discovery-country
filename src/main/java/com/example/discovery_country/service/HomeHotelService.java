package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.exception.ActivityNotFoundException;
import com.example.discovery_country.exception.HomeHotelNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.helper.RatingHelper;
import com.example.discovery_country.helper.UpdateLike;
import com.example.discovery_country.mapper.HomeHotelMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.criteria.HomeHotelCriteriaRequest;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.HomeHotelResponse;
import com.example.discovery_country.model.dto.response.HomeHotelResponseFindById;
import com.example.discovery_country.service.specification.HomeHotelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeHotelService {
    private final HomeHotelMapper homeHotelMapper;
    private final HomeHotelRepository homeHotelRepository;
    private final ImageRepository imageRepository;
    private final IncreaseViewCount viewCount;
    private final RatingHelper ratingHelper;
    private final UpdateLike updateLike;

    public HomeHotelResponse create(HomeHotelRequest homeHotelRequest) {

        log.info("ActionLog.createHomeHotel start");

        HomeHotelEntity homeHotelEntity = homeHotelMapper.mapToEntity(homeHotelRequest, imageRepository.findAllById(homeHotelRequest.getImageIds()));
        HomeHotelResponse homeHotelResponse = homeHotelMapper.mapToResponse(homeHotelRepository.save(homeHotelEntity), LangType.AZ);

        log.info("ActionLog.createHomeHotel end");

        return homeHotelResponse;

    }


    public HomeHotelResponseFindById homeHotelResponseFindById(Long id,LangType key) {

        log.info("ActionLog.homeHotelResponseFindById start with id#" + id);

        HomeHotelEntity homeHotelEntity = homeHotelRepository.findOne(HomeHotelSpecification.findByIdWithFilters(id)).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));
      homeHotelEntity.setRooms(homeHotelEntity.getRooms().stream().filter(i->
              i.isAvailable()&&!i.isDeleted()).collect(Collectors.toSet()));
        viewCount.updateViewCount(homeHotelEntity);
        log.info("ActionLog.homeHotelResponseFindById end");

        return homeHotelMapper.mapToHomeHotelResponseFindById(homeHotelEntity,key);
    }

    public Page<HomeHotelResponse> getHomeHotels(Pageable page, HomeHotelCriteriaRequest criteria) {

        log.info("ActionLog.getHomes start");
        System.out.println(criteria.getName()+criteria.getType());
        Specification<HomeHotelEntity> spec = HomeHotelSpecification.homeByCriteria(criteria);
        Page<HomeHotelEntity> list = homeHotelRepository.findAll(spec, page);

        List<HomeHotelResponse> list1 = list.map(i -> homeHotelMapper.mapToResponse(i, criteria.getKey())).toList();

        log.info("ActionLog.getHomes end");
        return new PageImpl<>(list1);
    }

    public HomeHotelResponse updateHomeHotel(Long id, HomeHotelRequest homeHotelRequest) {
        log.info("ActionLog.updateHomeHotel start with id#" + id);

        HomeHotelEntity homeHotelEntity = homeHotelRepository.findById(id).orElseThrow(() ->
                new HomeHotelNotFoundException(HttpStatus.NOT_FOUND.name(), "Home Hotel not found"));
        homeHotelMapper.mapForUpdate(homeHotelEntity, homeHotelRequest);
        homeHotelEntity = homeHotelRepository.save(homeHotelEntity);

        log.info("ActionLog.updateHomeHotel end");

        return homeHotelMapper.mapToResponse(homeHotelEntity,LangType.AZ);
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
