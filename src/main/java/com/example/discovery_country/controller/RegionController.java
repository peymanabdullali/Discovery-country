package com.example.discovery_country.controller;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.*;
import com.example.discovery_country.model.dto.response.RegionResponse;
import com.example.discovery_country.model.dto.response.RegionResponseForFindById;
import com.example.discovery_country.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions")
@Slf4j
@RequiredArgsConstructor
public class RegionController {
    private final RegionService service;


    @PostMapping
    public ResponseEntity<RegionResponse> createRegion(@Valid @RequestBody RegionRequest request) {
        log.info("Controller.createRegion start");
        RegionResponse region = service.createRegion(request);
        log.info("Controller.createRegion end");
        return ResponseEntity.status(HttpStatus.CREATED).body(region);
    }

    @GetMapping
    public ResponseEntity<Page<RegionResponse>> getRegions(CriteriaRequestForName criteriaRequest,
                                                           Pageable pageable) {
        log.info("Controller.getZones start");
        Page<RegionResponse> regions = service.getRegions(criteriaRequest, pageable);

        log.info("Controller.getZones end");
        return ResponseEntity.ok(regions);
    }


    @DeleteMapping("/{id}")
    public void deleteRegion(
            @PathVariable Long id) {
        log.info("Controller.deleteRegion start with id#" + id);
        service.deleteRegion(id);
        log.info("Controller.deleteRegion end");
    }

    @GetMapping("/{id}")
    public RegionResponseForFindById findRegionById(
            @PathVariable Long id, LangType key) {
        log.info("Controller.findRegion start with id#" + id);
        RegionResponseForFindById regionById = service.findRegionById(id,key);
        log.info("Controller.findRegion end");
        return regionById;
    }

}
