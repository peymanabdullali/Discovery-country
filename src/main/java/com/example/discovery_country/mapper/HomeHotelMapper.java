package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HomeHotelMapper {

    @Mapping(target = "region.id", source = "homeHotelRequest.regionId")
    HomeHotelEntity mapToEntity(HomeHotelRequest homeHotelRequest, List<ImageEntity> images);


    default HomeHotelResponse mapToResponse(HomeHotelEntity entity, LangType key) {
        HomeHotelResponse responseBuilder = new HomeHotelResponse();
        responseBuilder.setId(entity.getId());
        responseBuilder.setName(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)));

        if (!entity.getImages().isEmpty()) {
            responseBuilder.setImage((mapImageResponseForHomeHotel(
                    entity.getImages().stream().
                            findFirst().orElseThrow(() -> new RuntimeException("IMAGE_NOT_FOUND"))
            )));
        }

        return responseBuilder;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    @Mapping(target = "description", expression = "java(entity.getDescription().getOrDefault(key, entity.getDescription().get(LangType.AZ)))")
    @Mapping(target = "address", expression = "java(entity.getAddress().getOrDefault(key, entity.getAddress().get(LangType.AZ)))")
    @Mapping(target = "rooms", expression = "java(mapEntitiesToRoomResponses(entity.getRooms(), key))")
    HomeHotelResponseFindById mapToHomeHotelResponseFindById(HomeHotelEntity entity, LangType key);
    default Set<RoomResponseForHomeHotel> mapEntitiesToRoomResponses(Set<RoomEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toSet()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RoomResponseForHomeHotel toResponseMany(RoomEntity entity, @Context LangType key);

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
