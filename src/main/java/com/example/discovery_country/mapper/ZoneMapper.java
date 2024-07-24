package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ZoneMapper {

    ZoneEntity mapToEntity(ZoneRequest request);

    ZoneResponse mapToResponse(ZoneEntity entity);

}
