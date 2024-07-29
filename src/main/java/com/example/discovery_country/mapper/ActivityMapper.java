package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL) // Və ya IGNORE seçin
public interface ActivityMapper {

    ActivityEntity mapToEntity(ActivityRequest activityRequest);

    ActivityResponse mapToResponse(ActivityEntity activityEntity);

    void mapForUpdate(@MappingTarget ActivityEntity activityEntity, ActivityRequest activityRequest);
}
