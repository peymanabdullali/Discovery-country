package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.enums.LangType;
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

    default ScenicSpotResponse mapToResponse(ScenicSpotEntity entity,LangType key){
        ScenicSpotResponse.ScenicSpotResponseBuilder name = ScenicSpotResponse.builder().
                id(entity.getId()).
                name(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)));
        if (!entity.getImages().isEmpty()) {
            name.image(mapToImageResponse(
                    entity.getImages().stream().filter(s->!s.isDeleted()).findFirst().orElseThrow()));
        }

        return name.build();}

    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    RegionResponseForRelations toResponseMany(RegionEntity entity, @Context LangType key);


    @Mapping(target = "name", expression = "java(entity.getName().getOrDefault(key, entity.getName().get(LangType.AZ)))")
    @Mapping(target = "description", expression = "java(entity.getDescription().getOrDefault(key, entity.getDescription().get(LangType.AZ)))")
    @Mapping(target = "region", expression = "java(toResponseMany(entity.getRegion(), key))")
    ScenicSpotResponseForFindById mapToResponseForFindById(ScenicSpotEntity entity,LangType key);

    ImageResponseForRelations mapToImageResponse(ImageEntity entities);

        @AfterMapping
        default void linkImages(@MappingTarget ScenicSpotEntity scenicSpot, List<ImageEntity> images) {
            for (ImageEntity image : images) {
                image.setScenicSpot(scenicSpot);
            }
            scenicSpot.setImages(images);
        }


}
