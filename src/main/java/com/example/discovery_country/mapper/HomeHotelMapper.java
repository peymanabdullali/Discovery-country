package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.HomeHotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HomeHotelMapper {

    HomeHotelEntity mapToEntity(HomeHotelRequest request);

    HomeHotelResponse mapToResponse(HomeHotelEntity entity);

}
