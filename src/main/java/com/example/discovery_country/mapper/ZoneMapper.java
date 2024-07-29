package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ZoneMapper {
//    @Mapping(target = "zone.id",source = "request.zoneId")

    ZoneEntity mapToEntity(ZoneRequest request, List<RegionEntity> regions);

    ZoneResponse mapToResponse(ZoneEntity entity);
    List<RegionResponseForZone> mapToRegionResponseList(List<RegionEntity> entities);
    List<ZoneResponse> mapToZoneResponseList(List<ZoneEntity> entities);
//    @Mapping(target = "zone.id",source = "request.zoneId")
    void mapForUpdate (@MappingTarget ZoneEntity entity, ZoneRequest request);


//    List<ActivityCategoryResponse> mapToActivityCategoryResponseList(List<RegionEntity> entities);
//    List<RestaurantResponse> mapToRestaurantResponseList(List<RegionEntity> entities);
//    List<HomeHotelResponse> mapToHomeHotelResponseList(List<RegionEntity> entities);
//    List<ScenicSpotResponse> mapToScenicSpotResponseList(List<RegionEntity> entities);


}
