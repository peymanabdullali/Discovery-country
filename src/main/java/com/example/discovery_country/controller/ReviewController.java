package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.*;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.ReviewService;
import com.example.discovery_country.service.ZoneService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(
            @RequestPart("file") MultipartFile file)
    {
        log.info("Controller.uploadFile start");

        String path = reviewService.addPhoto(file);
        log.info("Controller.uploadFile end");

        return path;
    }

//        @PostMapping(value = "/restaurant2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ReviewResponse> createRestaurantReview2(
//            @RequestParam("request") ReviewRequestForRestaurant request,
//            @RequestParam("file") MultipartFile file) {
//
//        log.info("Controller.createRestaurantReview start");
//
//        ReviewResponse response = reviewService.createRestaurantReview(request, file);
//
//        log.info("Controller.createRestaurantReview end");
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

//    @ApiOperation("Adding photo by productId")
//    @PostMapping(value = "/addPhoto3",produces = MediaType.MULTIPART_FORM_DATA)
//    public void createRestaurantReview3(@RequestPart ReviewRequestForRestaurant request, @RequestPart MultipartFile photo) {
//        reviewService.createRestaurantReview(request, photo);
//    }


    @PostMapping("/home-hotel")
    public ResponseEntity<ReviewResponse> createHomeHotelReview(@RequestPart("review") ReviewRequestForHomeHotel review,
                                                                @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createHomeHotelReview start");

        ReviewResponse response = reviewService.createHomeHotelReview(review,photo);

        log.info("Controller.createHomeHotelReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/scenic-spot")
    public ResponseEntity<ReviewResponse> createScenicSpotReview(@RequestPart("review") ReviewRequestForScenicSpots  review,
                                                                 @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createScenicSpotReview start");

        ReviewResponse response = reviewService.createScenicSpotReview(review,photo);

        log.info("Controller.createScenicSpotReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
        @PostMapping("/restaurant")
    public ResponseEntity<ReviewResponse> createRestaurantReview(@RequestPart("review") ReviewRequestForRestaurant review,
                                                                 @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createRestaurantReview start");

        ReviewResponse response = reviewService.createRestaurantReview(review,photo);

        log.info("Controller.createRestaurantReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}