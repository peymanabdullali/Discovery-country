package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.model.dto.request.RegionRequest;
import com.example.discovery_country.model.dto.response.RegionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {

    RegionEntity mapToEntity(RegionRequest request);

    RegionResponse mapToResponse(RegionEntity entity);

}
