package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponseForActivity;
import com.example.discovery_country.model.dto.response.ActivityResponse;
import com.example.discovery_country.model.dto.response.ActivityResponseForHomePage;
import com.example.discovery_country.model.dto.response.ImageResponseForActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL) // Və ya IGNORE seçin
public interface ActivityMapper {
    @Mapping(target = "activityCategory.id",source = "activityRequest.activityCategoryId")
    ActivityEntity mapToEntity(ActivityRequest activityRequest,List<ImageEntity> images);

    ActivityResponse mapToResponse(ActivityEntity activityEntity);
    List<ImageResponseForActivity> mapToImageResponse(List<ImageEntity> imageEntity);
    ActivityCategoryResponseForActivity mapToActivityCategoryResponse(ActivityCategoryEntity activityCategoryEntity);

    ActivityResponseForHomePage mapToActivityForHomePage(ActivityEntity activity);
    void mapForUpdate(@MappingTarget ActivityEntity activityEntity, ActivityRequest activityRequest);
}
