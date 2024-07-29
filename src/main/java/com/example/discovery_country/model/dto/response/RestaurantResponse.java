package com.example.discovery_country.model.dto.response;

import jakarta.persistence.Column;
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
public class RestaurantResponse {

    String name;

    String description;

    String address;

    String contact;

    String mapUrl;
    String menuUrl;
    RegionResponse region;
    List<ImageResponse> images;
    Long viewed;
}
