package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.repository.ActivityCategoryRepository;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.Status;
import com.example.discovery_country.exception.ActivityNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.helper.RatingHelper;
import com.example.discovery_country.helper.UpdateLike;
import com.example.discovery_country.mapper.ActivityMapper;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;

import com.example.discovery_country.model.dto.response.ActivityResponseFindById;
import com.example.discovery_country.service.specification.ActivitySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {
    public final ActivityRepository activityRepository;
    public final ImageRepository imageRepository;
    private final ActivityMapper activityMapper;
    private final IncreaseViewCount viewCount;
    private final RatingHelper ratingHelper;
    private final UpdateLike updateLike;

    public ActivityResponse create(ActivityRequest request) {
        log.info("ActionLog.createActivity start");


        ActivityEntity activityEntity = activityMapper.mapToEntity(request, imageRepository.findAllById(request.getImageIds()));
        ActivityResponse activityResponse = activityMapper.mapToResponse(activityRepository.save(activityEntity), LangType.AZ);
        log.info("ActionLog.createActivity end");
        return activityResponse;
    }

    public Page<ActivityResponse> getActivities(ActivityCriteriaRequest criteriaRequest, Pageable pageable) {
        log.info("ActionLog.getActivity start");

        Specification<ActivityEntity> spec = ActivitySpecification.getActivityByCriteria(criteriaRequest);
        Page<ActivityEntity> activities = activityRepository.findAll(spec, pageable);

        log.info("ActionLog.getActivity end");
        return activities.map(i -> activityMapper.mapToResponse(i, criteriaRequest.getKey()));
    }

    public ActivityResponseFindById activityResponseFindById(Long id,LangType key) {

        log.info("ActionLog.activityResponseFindById start with id#" + id);

        ActivityEntity activityEntity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));
        viewCount.updateViewCount(activityEntity);
        activityEntity.setImages(activityEntity.getImages().stream().filter(i -> !i.isDeleted()).toList());

        if (activityEntity.getActivityStatus() == Status.INACTIVE) {
            throw new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity is not active");

        }

        log.info("ActionLog.activityResponseFindById end");

        return activityMapper.mapToActivityResponseFindById(activityEntity,key);

    }

//    public ActivityResponse getActivity(Long id,LangType key) {
//
//        log.info("ActionLog.getActivity start with id#" + id);
//
//        ActivityEntity activity = activityRepository.findById(id).orElseThrow(() ->
//                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));
//        activity.setViewed(activity.getViewed() + 1);
//        activityRepository.save(activity);
//
//        log.info("ActionLog.getActivity end with id#" + id);
//
//        return activityMapper.mapToResponse(activity,key);
//    }

    public ActivityResponse update(Long id, ActivityRequest activityRequest) {
        log.info("ActionLog.updateActivity start with id#" + id);

        ActivityEntity activityEntity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));
        activityMapper.mapForUpdate(activityEntity, activityRequest);
        activityEntity = activityRepository.save(activityEntity);

        log.info("ActionLog.updateActivity end");

        return activityMapper.mapToResponse(activityEntity,LangType.AZ);
    }

    public void softDelete(Long id) {
        activityRepository.softDelete(id);
    }


    public void updateLikeCount(Long id, boolean increment) {
        ActivityEntity activity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));

        updateLike.updateLikeCount(activity, increment);
    }

    public void updateAverageRating(Long id, int stars) {
        ActivityEntity activity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));

        ratingHelper.addRating(activity, stars);
    }

}
