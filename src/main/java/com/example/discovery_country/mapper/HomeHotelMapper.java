package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface    HomeHotelMapper {

    @Mapping(target = "region.id", source = "homeHotelRequest.regionId")
    HomeHotelEntity mapToEntity(HomeHotelRequest homeHotelRequest, List<ImageEntity> images);

    List<HomeHotelResponse> mapToResponseList(List<HomeHotelEntity> entityList);

    default HomeHotelResponse mapToResponse(HomeHotelEntity entity) {
        HomeHotelResponse responseBuilder = new HomeHotelResponse();
        responseBuilder.setId(entity.getId());
        responseBuilder.setName(entity.getName());

        if (!entity.getImages().isEmpty()) {
            responseBuilder.setImage((mapImageResponseForHomeHotel(
                    entity.getImages().stream().
                            findFirst().orElseThrow(()->new RuntimeException("IMAGE_NOT_FOUND"))
            )));
        }

        return responseBuilder;
    }

    HomeHotelResponseFindById mapToHomeHotelResponseFindById(HomeHotelEntity homeHotelEntity);

//    RoomResponseForHomeHotel mapToRoomResponse(RoomEntity entity);

//    ReviewResponse mapToReviewResponse(ReviewEntity entity);

    ImageResponseForRelations mapImageResponseForHomeHotel(ImageEntity entity);

    void mapForUpdate(@MappingTarget HomeHotelEntity homeHotelEntity, HomeHotelRequest homeHotelRequest);

    @AfterMapping
    default void linkImages(@MappingTarget HomeHotelEntity homeHotel, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setHomeHotel(homeHotel);
        }
        homeHotel.setImages(images);
    }
}
