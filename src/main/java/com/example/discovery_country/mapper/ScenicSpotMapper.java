package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScenicSpotMapper {
    @Mapping(target = "region.id",source = "request.regionId")
    ScenicSpotEntity mapToEntity(ScenicSpotRequest request,List<ImageEntity> images);

    ScenicSpotResponse mapToResponse(ScenicSpotEntity entity);
    List<ScenicSpotResponse> mapToResponseList(List<ScenicSpotEntity> entities);
    List<ImageResponse> mapScenicSpotResponseList(List<ImageEntity> entities);


}
