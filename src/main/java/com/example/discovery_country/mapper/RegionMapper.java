package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.RegionRequest;
import com.example.discovery_country.model.dto.response.*;
import org.apache.commons.codec.language.bm.Lang;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {
    @Mapping(target = "zone.id", source = "request.zoneId")
    RegionEntity mapToEntity(RegionRequest request);

//    List<RestaurantResponseForRegion> mapToRestaurantResponseList(List<RestaurantEntity> entities);

    default List<ScenicSpotResponse> mapEntitiesToScenicSpotResponses(List<ScenicSpotEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toList()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    ScenicSpotResponse toResponseMany(ScenicSpotEntity entity, @Context LangType key);


    default List<RestaurantResponse> mapEntitiesToRestaurantResponses(List<RestaurantEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toList()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RestaurantResponse toResponseMany(RestaurantEntity entity, @Context LangType key);


    default List<HomeHotelResponse> mapEntitiesToHomeHotelResponses(List<HomeHotelEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toList()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    HomeHotelResponse toResponseMany(HomeHotelEntity entity, @Context LangType key);

    default List<ActivityCategoryResponse> mapEntitiesToActCategoryResponses(List<ActivityCategoryEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toList()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    ActivityCategoryResponse toResponseMany(ActivityCategoryEntity entity, @Context LangType key);

    @Mapping(target = "name",
            expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    @Mapping(target = "scenicSpots", expression = "java(mapEntitiesToScenicSpotResponses(entity.getScenicSpots(), key))")
    @Mapping(target = "homeHotels", expression = "java(mapEntitiesToHomeHotelResponses(entity.getHomeHotels(), key))")
    @Mapping(target = "activityCategories", expression = "java(mapEntitiesToActCategoryResponses(entity.getActivityCategories(), key))")
    @Mapping(target = "zone", expression = "java(toResponseMany(entity.getZone(), key))")
    @Mapping(target = "restaurants", expression = "java(mapEntitiesToRestaurantResponses(entity.getRestaurants(), key))")
    RegionResponseForFindById mapToResponseForFindById(RegionEntity entity, LangType key);

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    ZoneResponseForRegion toResponseMany(ZoneEntity entity, @Context LangType key);


    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RegionResponse mapToResponse(RegionEntity entity, LangType key);
}
