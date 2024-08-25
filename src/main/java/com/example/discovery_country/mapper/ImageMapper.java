package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers; // Bu import yalnız istifadə olunacaqsa saxlanılır

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL) // Və ya IGNORE seçin
public interface ImageMapper {
    ImageEntity mapToEntity(String name, String url);

    ImageResponse mapToResponse(ImageEntity imageEntity);

    void mapForUpdate(@MappingTarget ImageEntity imageEntity, ImageRequest imageRequest);
}
