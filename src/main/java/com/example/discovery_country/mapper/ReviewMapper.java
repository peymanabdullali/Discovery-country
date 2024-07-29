package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.model.dto.request.ReviewRequest;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {

//    ReviewEntity mapToEntity(ReviewRequest request);
//
//    ReviewResponse mapToResponse(ReviewEntity entity);

}
