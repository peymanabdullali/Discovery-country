package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import com.example.discovery_country.model.dto.response.RegionResponseForRelations;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ActivityCategoryMapper {
   ActivityCategoryMapper INSTANCE = Mappers.getMapper(ActivityCategoryMapper.class);


    ActivityCategoryEntity mapToEntity(ActivityCategoryRequest activityCategoryRequest);

    ActivityCategoryResponse mapToResponse(ActivityCategoryEntity activityCategoryEntity);

//    List<RegionResponseForRelations> mapToRegionResponseForActivityCategoryList(List<RegionEntity> entities);


    void mapForUpdate (@MappingTarget ActivityCategoryEntity entity, ActivityCategoryRequest request);
}
