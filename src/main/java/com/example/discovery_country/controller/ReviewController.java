package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.ReviewRequestForHomeHotel;
import com.example.discovery_country.model.dto.request.ReviewRequestForRestaurant;
import com.example.discovery_country.model.dto.request.ReviewRequestForScenicSpots;
import com.example.discovery_country.model.dto.request.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.ReviewService;
import com.example.discovery_country.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/restaurant")
    public ResponseEntity<ReviewResponse> createRestaurantReview(@RequestBody ReviewRequestForRestaurant request) {
        log.info("Controller.createRestaurantReview start");

        ReviewResponse response = reviewService.createRestaurantReview(request);

        log.info("Controller.createRestaurantReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/review/home-hotel")
    public ResponseEntity<ReviewResponse> createHomeHotelReview(@RequestBody ReviewRequestForHomeHotel request) {
        log.info("Controller.createHomeHotelReview start");

        ReviewResponse response = reviewService.createHomeHotelReview(request);

        log.info("Controller.createHomeHotelReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/review/scenic-spot")
    public ResponseEntity<ReviewResponse> createScenicSpotReview(@RequestBody ReviewRequestForScenicSpots request) {
        log.info("Controller.createScenicSpotReview start");

        ReviewResponse response = reviewService.createScenicSpotReview(request);

        log.info("Controller.createScenicSpotReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}