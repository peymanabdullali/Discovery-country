package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.exception.ActivityNotFoundException;
import com.example.discovery_country.mapper.ActivityMapper;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;

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
    public final ActivityMapper activityMapper;

    public ActivityResponse create(ActivityRequest request){
        log.info("ActionLog.createActivity start");

        ActivityEntity activityEntity = activityMapper.mapToEntity(request);
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


}
