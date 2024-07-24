package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
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
public class RegionRequest {

    @Column(nullable = false)
    String name;

    @Column(name = "map_url")
    String mapUrl;

    Long zoneId;
    List<Long> activityCategoryIds;
    List<Long> restaurantIds;
    List<Long> homeHotelIds;
    List<Long> scenicSpotIds;


}
