package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ActivityCategoryMapper {

        ActivityCategoryEntity mapToEntity(ActivityCategoryRequest activityCategoryRequest);

        ActivityCategoryResponse mapToResponse(ActivityCategoryEntity activityCategoryEntity);

        void mapForUpdate (@MappingTarget ActivityCategoryEntity entity, ActivityCategoryRequest request);



}
