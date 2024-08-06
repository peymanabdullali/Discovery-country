package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.ImageResponse;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import com.example.discovery_country.model.dto.response.ScenicSpotResponse;
import com.example.discovery_country.model.dto.response.ScenicSpotResponseForFindById;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScenicSpotMapper {
    @Mapping(target = "region.id", source = "request.regionId")
    ScenicSpotEntity mapToEntity(ScenicSpotRequest request, List<ImageEntity> images);

    default ScenicSpotResponse mapToResponse(ScenicSpotEntity entity){
        return ScenicSpotResponse.builder().
                id(entity.getId()).
                name(entity.getName()).
                image(mapToImageResponse(entity.getImages().get(0))).build();
    }
    ScenicSpotResponseForFindById mapToResponseForFindById(ScenicSpotEntity entity);

    List<ScenicSpotResponse> mapToResponseList(List<ScenicSpotEntity> entities);

    List<ImageResponse> mapScenicSpotResponseList(List<ImageEntity> entities);
    ImageResponse mapToImageResponse(ImageEntity entities);
    ReviewResponse mapToReviewResponse(ReviewEntity entities);


    @AfterMapping
    default void linkImages(@MappingTarget ScenicSpotEntity scenicSpot, List<ImageEntity> images) {
        for (ImageEntity image : images) {
            image.setScenicSpot(scenicSpot);
        }
        scenicSpot.setImages(images);
    }


}
