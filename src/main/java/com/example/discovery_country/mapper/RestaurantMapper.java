package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.RestaurantRequest;
import com.example.discovery_country.model.dto.request.RestaurantRequestForUpdate;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {
    @Mapping(target = "region.id", source = "request.regionId")
    RestaurantEntity mapToEntity(RestaurantRequest request, List<ImageEntity> images);

    default RestaurantResponse mapToResponse(RestaurantEntity entity) {
        RestaurantResponse.RestaurantResponseBuilder name = RestaurantResponse.builder().
                id(entity.getId()).
                name(entity.getName());
        if (!entity.getImages().isEmpty()) {
            name.image(mapToImageResponse(entity.getImages().stream().filter(s->!s.isDeleted()).findFirst().orElseThrow()));
        }
        return name.build();
    }

    void updateRestaurant(@MappingTarget RestaurantEntity entity, RestaurantRequestForUpdate request);

    RestaurantResponseForFindById mapToResponseForFindById(RestaurantEntity entity);

    List<RestaurantResponse> mapToResponseList(List<RestaurantEntity> entities);

    List<ImageResponse> mapImageResponseList(List<ImageEntity> entities);

    ImageResponse mapToImageResponse(ImageEntity entities);

    ReviewResponse mapToReviewResponse(ReviewEntity entities);


    @AfterMapping
    default void linkImages(@MappingTarget RestaurantEntity restaurant, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setRestaurant(restaurant);
        }
        restaurant.setImages(images);
    }

}
