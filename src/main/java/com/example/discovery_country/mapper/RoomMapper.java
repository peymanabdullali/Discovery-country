package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.request.RoomRequest;
import com.example.discovery_country.model.dto.response.RoomResponseFindById;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface RoomMapper {
    @Mapping(target = "homeHotel.id", source = "roomRequest.homeHotelId")
    RoomEntity mapToEntity(RoomRequest roomRequest, List<ImageEntity> images);

    default RoomResponse mapToResponse(RoomEntity entity) {
        RoomResponse.RoomResponseBuilder name = RoomResponse.builder().
                id(entity.getId()).
                name(entity.getName()).
                price(entity.getPrice()).
                roomType(entity.getRoomType());
        if (!entity.getImages().isEmpty()) {
            name.image(mapImageResponseForRoom(entity.getImages().stream().filter(s -> !s.isDeleted()).findFirst().orElseThrow()));
        }
        return name.build();
    }

//    List<ImageResponseForRelations> mapToImageResponse(List<ImageEntity> imageEntity);

//    HomeHotelResponseForRoom mapToHomeHotelResponse(HomeHotelEntity homeHotelEntity);

    RoomResponseFindById mapToRoomResponseFindById(RoomEntity roomEntity);

    ImageResponseForRelations mapImageResponseForRoom(ImageEntity entity);

    void mapForUpdate(@MappingTarget RoomEntity roomEntity, RoomRequest roomRequest);
}
