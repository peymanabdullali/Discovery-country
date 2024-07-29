package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.model.dto.request.RegionRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {
    @Mapping(target = "zone.id",source = "request.zoneId")
    RegionEntity mapToEntity(RegionRequest request);

    List<RegionResponse> mapEntityListToResponseList(List<RegionEntity> entities);

    List<ActivityCategoryResponse> mapToActivityCategoryResponseList(List<ActivityCategoryEntity> entities);
    List<RestaurantResponseForRegion> mapToRestaurantResponseList(List<RestaurantEntity> entities);
    List<ImageResponse> mapToImageResponseList(List<ImageEntity> entities);
    List<HomeHotelResponse> mapToHomeHotelResponseList(List<HomeHotelEntity> entities);
    List<ScenicSpotResponse> mapToScenicSpotResponseList(List<ScenicSpotEntity> entities);

//    default RegionResponse mapToResponse(RegionEntity entity) {
//        return RegionResponse.builder().name(entity.getName()).mapUrl(entity.getMapUrl()).build();
//    }
     RegionResponse mapToResponse(RegionEntity entity);

}
