package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.model.dto.request.ReviewRequestForHomeHotel;
import com.example.discovery_country.model.dto.request.ReviewRequestForRestaurant;
import com.example.discovery_country.model.dto.request.ReviewRequestForScenicSpots;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {
    @Mapping(target = "restaurant.id",source = "request.restaurantId")
    @Mapping(target = "user.id",source = "request.userId")
    ReviewEntity mapToEntity(ReviewRequestForRestaurant request);

    @Mapping(target = "homeHotel.id",source = "request.homeHotelId")
    @Mapping(target = "user.id",source = "request.userId")
    ReviewEntity mapToEntity(ReviewRequestForHomeHotel request);

    @Mapping(target = "scenicSpot.id",source = "request.scenicSpotId")
    @Mapping(target = "user.id",source = "request.userId")
    ReviewEntity mapToEntity(ReviewRequestForScenicSpots request);

    ReviewResponse mapToResponse(ReviewEntity entity);

}
