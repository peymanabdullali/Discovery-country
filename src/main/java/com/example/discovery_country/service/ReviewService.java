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
    }

    public ReviewResponse createHomeHotelReview(ReviewRequestForHomeHotel request) {
        log.info("ActionLog.createHomeHotelReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createHomeHotelReview end");
        return reviewResponse;
    }
}
