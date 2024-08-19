package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
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
public class RestaurantResponseForFindById {
    Long id;
    String name;
    String description;
    String address;

    String contact;

    RegionResponseForZone region;

    List<ImageResponse> images;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;

    double averageRating;
    String mapUrl;
    String menuUrl;
    Long viewed;
    boolean status;
}
