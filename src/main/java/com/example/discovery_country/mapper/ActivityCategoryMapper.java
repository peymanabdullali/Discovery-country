package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import com.example.discovery_country.model.dto.response.RegionResponseForRelations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ActivityCategoryMapper {
   ActivityCategoryMapper INSTANCE = Mappers.getMapper(ActivityCategoryMapper.class);


    ActivityCategoryEntity mapToEntity(ActivityCategoryRequest activityCategoryRequest);
    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    ActivityCategoryResponse mapToResponse(ActivityCategoryEntity entity, LangType key);



    void mapForUpdate (@MappingTarget ActivityCategoryEntity entity, ActivityCategoryRequest request);
}
