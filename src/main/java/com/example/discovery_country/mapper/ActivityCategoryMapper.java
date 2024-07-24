package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ActivityCategoryMapper {

    ActivityCategoryEntity mapToEntity(ActivityCategoryRequest request);

    ActivityCategoryResponse mapToResponse(ActivityCategoryEntity entity);

}
