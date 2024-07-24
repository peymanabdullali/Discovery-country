package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScenicSpotMapper {

    ScenicSpotEntity mapToEntity(ScenicSpotRequest request);

    ScenicSpotResponse mapToResponse(ScenicSpotEntity entity);

}
