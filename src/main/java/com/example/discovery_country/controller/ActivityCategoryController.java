package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import com.example.discovery_country.service.ActivityCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/api/activity-categories")
@RequiredArgsConstructor
public class ActivityCategoryController {

    private final ActivityCategoryService activityCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ActivityCategoryResponse> createActivityCategory(@RequestBody ActivityCategoryRequest request) {

        log.info("Controller.createActivityCategory start");

        ActivityCategoryResponse response = activityCategoryService.create(request);

        log.info("Controller.createActivityCategory end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ActivityCategoryResponse>> getActivityCategories(
            ActivityCategoryCriteriaRequest criteriaRequest,
            Pageable pageable) {
        log.info("Controller.getActivityCategories start");

        Page<ActivityCategoryResponse> response = activityCategoryService.getActivityCategories(criteriaRequest, pageable);

        log.info("Controller.getActivityCategories end");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActivityCategoryResponse> updateActivityCategory(
            @PathVariable Long id,
           @Valid @RequestBody ActivityCategoryRequest categoryRequest) {
        log.info("Controller.updateActivityCategory start with id#" + id);

        ActivityCategoryResponse response = activityCategoryService.update(id, categoryRequest);

        log.info("Controller.updateActivityCategory end");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteActivityCategory(@PathVariable Long id) {
        log.info("Controller.softDeleteActivityCategory start with id#" + id);

        activityCategoryService.softDelete(id);

        log.info("Controller.softDeleteActivityCategory end");

        return ResponseEntity.noContent().build();
    }
}

