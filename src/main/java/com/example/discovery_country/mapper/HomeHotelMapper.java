package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.model.dto.request.ActivityRequest;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HomeHotelMapper {

   //HomeHotelEntity mapToEntity(HomeHotelRequest homeHotelRequest);
    @Mapping(target ="region.id",source = "homeHotelRequest.regionId")
    HomeHotelEntity mapToEntity(HomeHotelRequest homeHotelRequest, List<ImageEntity> images);
    default HomeHotelResponse mapToResponse(HomeHotelEntity entity) {
        HomeHotelResponse.HomeHotelResponseBuilder name = HomeHotelResponse.builder().
                id(entity.getId()).
                name(entity.getName());
        if (!entity.getImages().isEmpty()) {
            name.image(mapImageResponseForHomeHotel(entity.getImages().stream().filter(s -> !s.getDeleted()).findFirst().orElseThrow()));
        }
        return name.build();

    }


    List<ImageResponseForHomeHotel> mapToImageResponse(List<ImageEntity> imageEntity);

    RegionResponseForHomeHotel mapToRegionResponse(RegionEntity regionEntity);

    HomeHotelResponseFindById mapToHomeHotelResponseFindById(HomeHotelEntity homeHotelEntity);

    ImageResponseForHomeHotel mapImageResponseForHomeHotel(ImageEntity entity);

    void mapForUpdate(@MappingTarget HomeHotelEntity homeHotelEntity, HomeHotelRequest homeHotelRequest);
}
