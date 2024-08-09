package com.example.discovery_country.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomsMapper {

//    HotelRoomsEntity mapToEntity(HotelRoomsRequest request);
//
//    HotelRoomsResponse mapToResponse(HotelRoomsEntity entity);

}
