package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.ScenicSpotRequest;
import com.example.discovery_country.model.dto.response.*;
import jakarta.mail.search.SearchTerm;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScenicSpotMapper {
    @Mapping(target = "region.id", source = "request.regionId")
    ScenicSpotEntity mapToEntity(ScenicSpotRequest request, List<ImageEntity> images);

    default ScenicSpotResponse mapToResponse(ScenicSpotEntity entity){
        ScenicSpotResponse.ScenicSpotResponseBuilder name = ScenicSpotResponse.builder().
                id(entity.getId()).
                name(entity.getName());
            name.image(mapToImageResponse(entity.getImages().stream().filter(s->!s.isDeleted()).findFirst().orElseThrow()));

        return name.build();}
    List<ScenicSpotResponse> mapToResponseList(List<ScenicSpotEntity> entities);

    ScenicSpotResponseForFindById mapToResponseForFindById(ScenicSpotEntity entity);

//    List<ImageResponseForRelations> mapScenicSpotResponseList(List<ImageEntity> entities);
    ImageResponseForRelations mapToImageResponse(ImageEntity entities);
//    ReviewResponse mapToReviewResponse(ReviewEntity entities);


        @AfterMapping
        default void linkImages(@MappingTarget ScenicSpotEntity scenicSpot, List<ImageEntity> images) {
            for (ImageEntity image : images) {
                image.setScenicSpot(scenicSpot);
            }
            scenicSpot.setImages(images);
        }


}
