package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RegionResponseForRelations;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomReservationMapper {
    @Mapping(target = "room.id", source = "request.roomId")
    @Mapping(target = "user.id", source = "request.userId")
    RoomReservationEntity mapToEntity(RoomReservationRequest request);
    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RoomReservationResponse mapToResponse(RoomReservationEntity entity,LangType key);


}
