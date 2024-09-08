package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.*;
import com.example.discovery_country.model.dto.request.auth.ChangePassword;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/zone")
@Slf4j
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService service;


    @PostMapping("/create")
    public ResponseEntity<ZoneResponse> createZone(@RequestBody ZoneRequest request) {

        log.info("Controller.createZone start");

        ZoneResponse response = service.create(request);

        log.info("Controller.createZone end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ZoneResponse>> getZones(CriteriaRequestForName criteriaRequest,
                                                       Pageable pageable) {
        log.info("Controller.getZones start");
        Page<ZoneResponse> zones = service.getZones(criteriaRequest, pageable);

        log.info("Controller.getZones end");
        return ResponseEntity.ok(zones);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ZoneResponse> updateZone(
            @PathVariable Long id,
            @RequestBody ZoneRequest zoneRequest) {
        log.info("Controller.updateZone start with id#" + id);

        ZoneResponse response = service.update(id, zoneRequest);

        log.info("Controller.updateZone end");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteZone(
            @PathVariable Long id) {
        log.info("Controller.deleteZone start with id#" + id);
        service.delete(id);
        log.info("Controller.deleteZone end");
    }

}

