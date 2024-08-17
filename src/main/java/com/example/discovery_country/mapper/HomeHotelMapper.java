package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HomeHotelMapper {

    @Mapping(target = "region.id", source = "homeHotelRequest.regionId")
    HomeHotelEntity mapToEntity(HomeHotelRequest homeHotelRequest, List<ImageEntity> images);

    List<HomeHotelResponse> mapToResponseList(List<HomeHotelEntity> entityList);

    default HomeHotelResponse mapToResponse(HomeHotelEntity entity) {
        HomeHotelResponse.HomeHotelResponseBuilder responseBuilder = HomeHotelResponse.builder()
                .id(entity.getId())
                .name(entity.getName());

        if (!entity.getImages().isEmpty()) {
            responseBuilder.image(mapImageResponseForHomeHotel(
                    entity.getImages().stream()
                            .filter(image -> !image.isDeleted())
                            .findFirst()
                            .orElseThrow()
            ));
        }

        return responseBuilder.build();
    }

    HomeHotelResponseFindById mapToHomeHotelResponseFindById(HomeHotelEntity homeHotelEntity);

    RoomResponseForHomeHotel mapToRoomResponse(RoomEntity entity);

    ReviewResponse mapToReviewResponse(ReviewEntity entity);

    ImageResponseForHomeHotel mapImageResponseForHomeHotel(ImageEntity entity);

    void mapForUpdate(@MappingTarget HomeHotelEntity homeHotelEntity, HomeHotelRequest homeHotelRequest);

    @AfterMapping
    default void linkImages(@MappingTarget HomeHotelEntity homeHotel, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setHomeHotel(homeHotel);
        }
        homeHotel.setImages(images);
    }
}
