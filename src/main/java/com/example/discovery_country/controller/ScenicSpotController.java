package com.example.discovery_country.controller;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import com.example.discovery_country.model.dto.response.ScenicSpotResponseForFindById;
import com.example.discovery_country.service.ScenicSpotService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("scenicSpot")
@Slf4j
@RequiredArgsConstructor
public class ScenicSpotController {
    private final ScenicSpotService service;

    @PostMapping
    public ResponseEntity<ScenicSpotResponse> createScenicSpot(@Valid @RequestBody ScenicSpotRequest request) {
        log.info("Controller.createScenicSpot start");

        ScenicSpotResponse scenicSpot = service.createScenicSpot(request);
        log.info("Controller.createScenicSpot end");
        return ResponseEntity.status(HttpStatus.CREATED).body(scenicSpot);
    }


    @GetMapping
    public ResponseEntity<Page<ScenicSpotResponse>> getScenicSpots(CriteriaRequestForName criteriaRequest,
                                                                   Pageable pageable) {
        log.info("Controller.getScenicSpots start");
        Page<ScenicSpotResponse> scenicSpots = service.getScenicSpots(criteriaRequest, pageable);

        log.info("Controller.getScenicSpots end");
        return ResponseEntity.ok(scenicSpots);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable Long id) {
        log.info("Controller.updateStatus start with id#" + id);
        service.updateStatus(id);
        log.info("Controller.updateStatus end");
    }

    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        log.info("Controller.softDelete start with id#" + id);
        service.softDelete(id);
        log.info("Controller.softDelete end");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScenicSpotResponseForFindById> findById(@PathVariable Long id, LangType key) {
        log.info("Controller.findScenicSpot start with id#" + id);
        ScenicSpotResponseForFindById byId = service.findById(id, key);
        log.info("Controller.findScenicSpot end");
        return ResponseEntity.ok(byId);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Void> likeScenicSpot(@PathVariable Long id, @RequestParam boolean increment) {
        service.updateLikeCount(id, increment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rate/{id}")
    public ResponseEntity<Void> rateScenicSpot(@PathVariable Long id, @RequestParam int stars) {
        service.rateScenicSpot(id, stars);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
