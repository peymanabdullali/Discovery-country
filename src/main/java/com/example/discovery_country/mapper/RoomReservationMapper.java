package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.RoomReservationEntity;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomReservationMapper {
    @Mapping(target = "room.id", source = "request.roomId")
    @Mapping(target = "user.id", source = "request.userId")
    @Mapping(target = "totalDay", expression = "java(calculateTotalDays(request.getEntryDate(), request.getExitDate()))")
    RoomReservationEntity mapToEntity(RoomReservationRequest request);
    default byte calculateTotalDays(LocalDate entryDate, LocalDate exitDate) {
        return (byte) java.time.temporal.ChronoUnit.DAYS.between(entryDate, exitDate);
    }
    RoomReservationResponse mapToResponse(RoomReservationEntity entity);
}
