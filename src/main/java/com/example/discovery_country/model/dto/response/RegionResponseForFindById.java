package com.example.discovery_country.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionResponseForFindById {
    String name;
    String mapUrl;
    ZoneResponseForRegion zone;
    List<ActivityCategoryResponse> activityCategories;
    List<RestaurantResponse> restaurants;
    List<HomeHotelResponse> homeHotels;
    List<ScenicSpotResponse> scenicSpots;
}
