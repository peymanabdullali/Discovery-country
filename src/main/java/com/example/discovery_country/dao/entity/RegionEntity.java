package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "regions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(name = "map_url")
    String mapUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ZoneEntity zone;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<ActivityCategoryEntity> activityCategories;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<RestaurantEntity> restaurants;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<HomeHotelEntity> homeHotels;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    List<ScenicSpotEntity> scenicSpots;


}
