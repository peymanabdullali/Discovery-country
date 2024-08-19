package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RoomReservationEntity;
import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomReservationMapper {
//    @Mapping(target = "room.id", source = "request.roomId")
//    @Mapping(target = "user.id", source = "request.userId")
    RoomReservationEntity mapToEntity(RoomReservationRequest request);

    @AfterMapping
    default void setDefaultStatus(@MappingTarget RoomReservationEntity entity) {
        entity.setStatus(true);
    }

    RoomReservationResponse mapToResponse(RoomReservationEntity entity);
}
