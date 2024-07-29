package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.RoomReservationEntity;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomReservationMapper {

//    RoomReservationEntity mapToEntity(RoomReservationRequest request);
//
//    RoomReservationResponse mapToResponse(RoomReservationEntity entity);

}
