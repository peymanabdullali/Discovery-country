package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.repository.ActivityCategoryRepository;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.exception.ActivityCategoryNotFoundException;
import com.example.discovery_country.exception.ActivityNotFoundException;
import com.example.discovery_country.mapper.ActivityMapper;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;

import com.example.discovery_country.model.dto.response.ActivityResponseForHomePage;
import com.example.discovery_country.service.specification.ActivitySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {
    public final ActivityRepository activityRepository;
    public final ActivityCategoryRepository categoryRepository;
    public final ImageRepository imageRepository;
    public final ActivityMapper activityMapper;

    public ActivityResponse create(ActivityRequest request){
        log.info("ActionLog.createActivity start");

       // ActivityCategoryEntity activityCategory=categoryRepository.findById(request.getActivityCategoryId()).orElseThrow(()->new ActivityCategoryNotFoundException(HttpStatus.NOT_FOUND.name(), "ActivityCategory not found"))

        ActivityEntity activityEntity = activityMapper.mapToEntity(request,imageRepository.findAllById(request.getImageIds()));
        ActivityEntity activity = activityRepository.save(activityEntity);

        log.info("ActionLog.createActivity end");

        return activityMapper.mapToResponse(activity);
    }

    public Page<ActivityResponse> getActivities(ActivityCriteriaRequest criteriaRequest, Pageable pageable){
        log.info("ActionLog.getActivity start");

        Specification<ActivityEntity> spec = ActivitySpecification.getActivityByCriteria(criteriaRequest);
        Page<ActivityEntity> activities = activityRepository.findAll(spec, pageable);

        log.info("ActionLog.getActivity end");

        return activities.map(activityMapper::mapToResponse);
    }

    public ActivityResponseForHomePage getActivityForHomePage(Long id){

        log.info("ActionLog.getActivityForHomePage start with id#" + id);

        ActivityEntity activityEntity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));

        log.info("ActionLog.getActivityForHomePage start with id#" + id);

        return activityMapper.mapToActivityForHomePage(activityEntity);

    }

    public ActivityResponse getActivity (Long id){

        log.info("ActionLog.getActivity start with id#" + id);

        ActivityEntity activity=activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));
        activity.setViewed(activity.getViewed()+1);
        activityRepository.save(activity);

        log.info("ActionLog.getActivity end with id#" + id);

        return activityMapper.mapToResponse(activity);
    }
    public ActivityResponse update(Long id, ActivityRequest activityRequest){
        log.info("ActionLog.updateActivity start with id#" + id);

        ActivityEntity activityEntity = activityRepository.findById(id).orElseThrow(() ->
                new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(), "Activity not found"));
    activityMapper.mapForUpdate(activityEntity, activityRequest);
        activityEntity = activityRepository.save(activityEntity);

        log.info("ActionLog.updateActivity end");

        return activityMapper.mapToResponse(activityEntity);
}

    public void softDelete(Long id){
        activityRepository.softDelete(id);
    }



    public void updateLikeCount(Long id ,boolean increment ){
        log.info("ActionLog.updateLikeCount start with id#" + id);

        ActivityEntity activity=activityRepository.findById(id).orElseThrow(()->new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(),"Activity not found"));
        activity.setLikeCount(increment? activity.getLikeCount()+1 : activity.getLikeCount()-1);
        activityRepository.save(activity);

        log.info("ActionLog.updateLikeCount end with id#" + id);
    }

    public void updateAverageRating(Long id, double rating) {
        log.info("ActionLog.updateAverageRating start with id#" + id);

        ActivityEntity activity=activityRepository.findById(id).orElseThrow(()->new ActivityNotFoundException(HttpStatus.NOT_FOUND.name(),"Activity not found"));
        double currentRating=activity.getAverageRating()==null? 0 : activity.getAverageRating();
        Long currentView=activity.getViewed();
        double newAverageRating=(currentRating * currentView + rating)/ (currentView + 1);
        activity.setAverageRating(newAverageRating);
        activity.setViewed(currentView+1);
        activityRepository.save(activity);

        log.info("ActionLog.updateAverageRating end with id#" + id);

    }
}
