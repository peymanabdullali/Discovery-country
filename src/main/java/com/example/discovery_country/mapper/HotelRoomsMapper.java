package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HotelRoomsEntity;
import com.example.discovery_country.model.dto.request.HotelRoomsRequest;
import com.example.discovery_country.model.dto.response.HotelRoomsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelRoomsMapper {

    HotelRoomsEntity mapToEntity(HotelRoomsRequest request);

    HotelRoomsResponse mapToResponse(HotelRoomsEntity entity);

}
