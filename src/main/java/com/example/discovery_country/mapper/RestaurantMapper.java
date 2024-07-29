package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.model.dto.request.RestaurantRequest;
import com.example.discovery_country.model.dto.response.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

//    RestaurantEntity mapToEntity(RestaurantRequest request);
//
//    RestaurantResponse mapToResponse(RestaurantEntity entity);

}
