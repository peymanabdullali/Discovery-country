package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.response.ActivityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ActivityMapper {

    ActivityEntity mapToEntity(ActivityRequest request);

    ActivityResponse mapToResponse(ActivityEntity entity);

}
