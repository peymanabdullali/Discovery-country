package com.example.discovery_country.model.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenicSpotResponseForFindById {


    String name;

    String description;

    RegionResponse region;
    List<ImageResponseForRelations> images;
    List<ReviewResponse> reviews;
    Long viewed;

}
