package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.enums.LangType;
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

    default RestaurantResponse mapToResponse(RestaurantEntity entity,LangType key) {
        RestaurantResponse.RestaurantResponseBuilder name = RestaurantResponse.builder().
                id(entity.getId()).
                name(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)));
        if (!entity.getImages().isEmpty()) {
            name.image(mapToImageResponse(entity.getImages().stream().filter(s->!s.isDeleted()).findFirst().orElseThrow()));
        }
        return name.build();
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RegionResponseForRelations toResponseMany(RegionEntity entity, @Context LangType key);


    void updateRestaurant(@MappingTarget RestaurantEntity entity, RestaurantRequestForUpdate request);

    @Mapping(target = "region", expression = "java(toResponseMany(entity.getRegion(), key))")
    @Mapping(target = "description", expression = "java(entity.getDescription().getOrDefault(key, entity.getDescription().get(LangType.AZ)))")
    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RestaurantResponseForFindById mapToResponseForFindById(RestaurantEntity entity,LangType key);

//    List<RestaurantResponse> mapToResponseList(List<RestaurantEntity> entities,LangType key);
    ImageResponseForRelations mapToImageResponse(ImageEntity entities);

    @AfterMapping
    default void linkImages(@MappingTarget RestaurantEntity restaurant, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setRestaurant(restaurant);
        }
        restaurant.setImages(images);
    }

}
