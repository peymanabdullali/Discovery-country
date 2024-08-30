package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.*;


import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ZoneMapper {
//    @Mapping(target = "zone.id",source = "request.zoneId")

    ZoneEntity mapToEntity(ZoneRequest request);


    default List<RegionResponseForRelations> mapEntitiesToResponses(List<RegionEntity> entities, LangType key) {
        return entities != null ? entities.stream()
                .map(entity -> toResponseMany(entity, key))
                .collect(Collectors.toList()) : null;
    }

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RegionResponseForRelations toResponseMany(RegionEntity entity, @Context LangType key);

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    @Mapping(target = "regions", expression = "java(mapEntitiesToResponses(entity.getRegions(), key))")
    ZoneResponse mapToResponse(ZoneEntity entity, LangType key);
    void mapForUpdate (@MappingTarget ZoneEntity entity, ZoneRequest request);




}
