package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.ImageEntity;
import jakarta.persistence.*;
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
public class ScenicSpotResponse {


    String name;

    String description;

//    RegionResponse region;
    List<ImageResponse> images;

    Long viewed;

}
