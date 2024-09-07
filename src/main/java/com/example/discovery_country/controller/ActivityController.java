package com.example.discovery_country.controller;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;
import com.example.discovery_country.model.dto.response.ActivityResponseFindById;
import com.example.discovery_country.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest request) {
        ActivityResponse response = activityService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ActivityResponse>> getActivities(ActivityCriteriaRequest criteriaRequest, Pageable pageable) {
        Page<ActivityResponse> responses = activityService.getActivities(criteriaRequest, pageable);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ActivityResponseFindById> getActivityForHomePage(@PathVariable Long id, LangType key) {
        ActivityResponseFindById response = activityService.activityResponseFindById(id,key);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Long id, @RequestBody ActivityRequest request) {

        ActivityResponse response = activityService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteActivity(@PathVariable Long id) {
        activityService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("like/{id}")
    public ResponseEntity<Void> updateLikeCount(@PathVariable Long id, @RequestParam boolean increment){
        activityService.updateLikeCount(id,increment);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("averageRating/{id}")
    public ResponseEntity<?> updateAverageRating(@PathVariable Long id, @RequestParam int stars) {
        activityService.updateAverageRating(id, stars);
        return ResponseEntity.ok().build();
    }
}
