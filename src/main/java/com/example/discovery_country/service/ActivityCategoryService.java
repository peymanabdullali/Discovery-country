package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.ActivityCategoryRepository;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.exception.ActivityCategoryNotFoundException;
import com.example.discovery_country.mapper.ActivityCategoryMapper;
import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import com.example.discovery_country.service.specification.ActivityCategorySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.discovery_country.dao.entity.ActivityCategoryEntity;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityCategoryService {
    private final ActivityCategoryRepository activityCategoryRepository;
    private final RegionRepository regionRepository;
    private final ActivityRepository activityRepository;

    public ActivityCategoryResponse create(ActivityCategoryRequest request) {
        log.info("ActionLog.createActivityCategory start");

        if (request.getRegionIds() == null || request.getRegionIds().isEmpty()) {
            throw new IllegalArgumentException("Region IDs must not be null or empty");
        }

       List<RegionEntity> regions=regionRepository.findAllById(request.getRegionIds());
       if (regions.isEmpty()){
           throw new RuntimeException("regions not founds");
       }

        ActivityCategoryEntity activityCategoryEntity = ActivityCategoryMapper.INSTANCE.mapToEntity(request);

        activityCategoryEntity.setRegions(regions);


        ActivityCategoryEntity activityCategory = activityCategoryRepository.save(activityCategoryEntity);

        log.info("ActionLog.createActivityCategory end");

        return ActivityCategoryMapper.INSTANCE.mapToResponse(activityCategory);

    }

    public Page<ActivityCategoryResponse> getActivityCategories(ActivityCategoryCriteriaRequest criteriaRequest, Pageable pageable){
        log.info("ActionLog.getActivityCategory start");

        Specification<ActivityCategoryEntity> spec= ActivityCategorySpecification.getActivityCategoryByCriteria(criteriaRequest);
        Page<ActivityCategoryEntity> activityCategories=activityCategoryRepository.findAll(spec,pageable);

        log.info("ActionLog.getActivityCategory end");

        return activityCategories.map(ActivityCategoryMapper.INSTANCE::mapToResponse);

    }

    public ActivityCategoryResponse update(Long id ,ActivityCategoryRequest categoryRequest){

        log.info("ActionLog.updateActivityCategory start with id#" + id);

        ActivityCategoryEntity categoryEntity=activityCategoryRepository.findById(id).orElseThrow(()->new ActivityCategoryNotFoundException(HttpStatus.NOT_FOUND.name(),"Activity Category not found"));
        ActivityCategoryMapper.INSTANCE.mapForUpdate(categoryEntity,categoryRequest);
        categoryEntity=activityCategoryRepository.save(categoryEntity);

        log.info("ActionLog.updateActivityCategory end");

        return ActivityCategoryMapper.INSTANCE.mapToResponse(categoryEntity);

    }

    public void softDelete(Long id ){
        activityCategoryRepository.softDelete(id);
    }

}
