package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.request.ImageRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImageMapper {

    ImageEntity mapToEntity(ImageRequest request);

    ImageResponse mapToResponse(ImageEntity imageEntity);

    void mapForUpdate(@MappingTarget ImageEntity imageEntity,ImageRequest imageRequest);

}
