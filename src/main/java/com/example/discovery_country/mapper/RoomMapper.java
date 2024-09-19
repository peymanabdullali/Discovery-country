package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.RoomRequest;
import com.example.discovery_country.model.dto.response.RoomResponseFindById;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    @Mapping(target = "homeHotel.id", source = "request.homeHotelId")
    RoomEntity mapToEntity(RoomRequest request, List<ImageEntity> images);

    @AfterMapping
    default void linkImages(@MappingTarget RoomEntity room, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setRoom(room);
        }
        room.setImages(images);
    }

    default RoomResponse mapToResponse(RoomEntity entity,LangType key) {
        RoomResponse.RoomResponseBuilder name = RoomResponse.builder().
                id(entity.getId()).
                name(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ))).
                price(entity.getPrice()).
                roomType(entity.getRoomType());
        if (!entity.getImages().isEmpty()) {
            name.image(mapImageResponseForRoom(entity.getImages().stream().filter(s -> !s.isDeleted()).findFirst().orElseThrow()));
        }
        return name.build();
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    HomeHotelResponseForRoom toResponseMany(HomeHotelEntity entity, @Context LangType key);
    @Mapping(target = "homeHotel", expression = "java(toResponseMany(entity.getHomeHotel(), key))")
    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    @Mapping(target = "amenities", expression = "java(entity.getAmenities().getOrDefault(key, entity.getAmenities().get(LangType.AZ)))")
    RoomResponseFindById mapToRoomResponseFindById(RoomEntity entity, LangType key);

    ImageResponseForRelations mapImageResponseForRoom(ImageEntity entity);
//    void mapForUpdate(@MappingTarget RoomEntity roomEntity, RoomRequest roomRequest);
}
