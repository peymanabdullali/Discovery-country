package com.example.discovery_country.model.dto.response;

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
public class RegionResponse {
    
    String name;

    String mapUrl;

    ZoneResponse zoneId;
    List<ActivityCategoryResponse> activityCategories;
    List<RestaurantResponse> restaurants;
    List<HomeHotelResponse> homeHotels;
    List<ScenicSpotResponse> scenicSpots;


}
