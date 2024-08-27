package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "regions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    String mapUrl;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    double longitude;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ZoneEntity zone;

    @ManyToMany(mappedBy = "regions", cascade = CascadeType.ALL)
    List<ActivityCategoryEntity> activityCategories;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<RestaurantEntity> restaurants;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<HomeHotelEntity> homeHotels;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<ScenicSpotEntity> scenicSpots;

}
