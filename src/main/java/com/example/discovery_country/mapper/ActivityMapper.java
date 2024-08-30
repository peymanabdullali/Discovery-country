package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
// Və ya IGNORE seçin
public interface ActivityMapper {
    @Mapping(target = "activityCategory.id", source = "activityRequest.activityCategoryId")
    ActivityEntity mapToEntity(ActivityRequest activityRequest, List<ImageEntity> images);

    default ActivityResponse mapToResponse(ActivityEntity entity,LangType key) {
        ActivityResponse.ActivityResponseBuilder name = ActivityResponse.builder().
                id(entity.getId()).
                name(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)));
        if (!entity.getImages().isEmpty()) {
            name.image((mapImageResponseForRelations(
                    entity.getImages().stream().
                            findFirst().orElseThrow(()->new RuntimeException("IMAGE_NOT_FOUND"))
            )));
        }
        return name.build();

    }

    @Mapping(target = "description", expression = "java(entity.getDescription().getOrDefault(key, entity.getDescription().get(LangType.AZ)))")
    @Mapping(target = "requirements", expression = "java(entity.getRequirements().getOrDefault(key, entity.getRequirements().get(LangType.AZ)))")
    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    ActivityResponseFindById mapToActivityResponseFindById(ActivityEntity entity, LangType key);

    ImageResponseForRelations mapImageResponseForRelations(ImageEntity entity);

    void mapForUpdate(@MappingTarget ActivityEntity activityEntity, ActivityRequest activityRequest);
    @AfterMapping
    default void linkImages(@MappingTarget ActivityEntity activity, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setActivity(activity);
        }
        activity.setImages(images);
    }

}
