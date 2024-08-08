package com.example.discovery_country.controller;

import com.example.discovery_country.service.HomeHotelService;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.HomeHotelResponse;
import com.example.discovery_country.model.dto.response.HomeHotelResponseFindById;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home-hotels")
@RequiredArgsConstructor
public class HomeHotelController {

    private final HomeHotelService homeHotelService;

    @PostMapping
    public ResponseEntity<HomeHotelResponse> createHomeHotel(@RequestBody HomeHotelRequest homeHotelRequest) {
        HomeHotelResponse homeHotelResponse = homeHotelService.create(homeHotelRequest);
        return new ResponseEntity<>(homeHotelResponse, HttpStatus.CREATED);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<HomeHotelResponseFindById> getHomeHotelById(@PathVariable Long id) {
        HomeHotelResponseFindById homeHotelResponseFindById = homeHotelService.homeHotelResponseFindById(id);
        return new ResponseEntity<>(homeHotelResponseFindById, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeHotelResponse> getHomeHotel(@PathVariable Long id) {
        HomeHotelResponse homeHotelResponse = homeHotelService.getHomeHotel(id);
        return new ResponseEntity<>(homeHotelResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HomeHotelResponse> updateHomeHotel(
            @PathVariable Long id,
            @RequestBody HomeHotelRequest homeHotelRequest) {
        HomeHotelResponse homeHotelResponse = homeHotelService.updateHomeHotel(id, homeHotelRequest);
        return new ResponseEntity<>(homeHotelResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteHomeHotel(@PathVariable Long id) {
        homeHotelService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> rateHomeHotel(@PathVariable Long id, @RequestParam int stars) {
        homeHotelService.rateHomeHotel(id, stars);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikeCount(@PathVariable Long id, @RequestParam boolean increment) {
        homeHotelService.updateLikeCount(id, increment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
