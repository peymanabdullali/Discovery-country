package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ImageMapper {

//    ImageEntity mapToEntity(ImageRequest request);

//    ImageResponse mapToResponse(ImageEntity entity);

}
