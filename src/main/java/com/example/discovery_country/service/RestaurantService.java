package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.dao.repository.RestaurantRepository;
import com.example.discovery_country.exception.HomeHotelNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.helper.RatingHelper;
import com.example.discovery_country.helper.UpdateLike;
import com.example.discovery_country.mapper.RestaurantMapper;
import com.example.discovery_country.model.dto.criteria.RestaurantCriteriaRequest;
import com.example.discovery_country.model.dto.request.RestaurantRequest;
import com.example.discovery_country.model.dto.request.RestaurantRequestForUpdate;
import com.example.discovery_country.model.dto.response.RestaurantResponse;
import com.example.discovery_country.model.dto.response.RestaurantResponseForFindById;
import com.example.discovery_country.service.specification.RegionSpecification;
import com.example.discovery_country.service.specification.RestaurantSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ImageRepository imageRepository;
    private final RestaurantMapper restaurantMapper;
    private final IncreaseViewCount viewCount;
    private final RatingHelper ratingHelper;
    private final UpdateLike updateLike;
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        log.info("ActionLog.createRestaurant start");
        RestaurantEntity restaurantEntity = restaurantMapper.mapToEntity(request, imageRepository.findAllById(request.getImageIds()));
        restaurantRepository.save(restaurantEntity);
        RestaurantResponse restaurantResponse = restaurantMapper.mapToResponse(restaurantEntity);
        log.info("ActionLog.createRestaurant end");
        return restaurantResponse;
    }

    public Page<RestaurantResponse> getRestaurants(RestaurantCriteriaRequest criteriaRequest, Pageable page) {
        log.info("ActionLog.getRestaurants start");
        Specification<RestaurantEntity> spec = RestaurantSpecification.getRestaurants(criteriaRequest);
        List<RestaurantResponse> restaurantResponses = restaurantMapper.mapToResponseList(restaurantRepository.findAll(spec, page).toList());
        log.info("ActionLog.getRestaurants end");
        return new PageImpl<>(restaurantResponses.stream().toList());
    }

    public RestaurantResponseForFindById findRestaurantById(long id) {
        log.info("ActionLog.findRestaurantById start with id#" + id);
        RestaurantEntity restaurant = restaurantRepository.
                findByIdAndStatusFalse(id).orElseThrow(() -> new RuntimeException("RESTAURANT_NOT_FOuND"));
        viewCount.updateViewCount(restaurant);

        restaurant.setReviews(restaurant.getReviews().stream().filter(i -> !i.isStatus()).toList());
        restaurant.setImages(restaurant.getImages().stream().filter(i -> !i.getDeleted()).toList());

        RestaurantResponseForFindById restaurantResponseForFindById = restaurantMapper.mapToResponseForFindById(restaurant);
        log.info("ActionLog.findRestaurantById end");
        return restaurantResponseForFindById;
    }

    public void softDelete(Long id) {
        log.info("ActionLog.softDelete start with id#" + id);
        restaurantRepository.softDelete(id);
        log.info("ActionLog.softDelete end");
    }

    public void updateRestaurant(long id, RestaurantRequestForUpdate request) {
        log.info("ActionLog.updateRestaurant start with id#" + id);
        RestaurantEntity restaurantEntity = restaurantRepository.
                findById(id).orElseThrow(() -> new RuntimeException("RESTAURANT_NOT_FOUND"));
        restaurantMapper.updateRestaurant(restaurantEntity, request);
        restaurantRepository.save(restaurantEntity);
        log.info("ActionLog.updateRestaurant end");
    }

    public void rateRestaurant(Long id, int stars) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Restaurant not found"));

        ratingHelper.addRating(restaurant, stars);
    }

    public void updateLikeCount(Long id, boolean increment) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Activity not found"));

        updateLike.updateLikeCount(restaurant, increment);
    }
}
