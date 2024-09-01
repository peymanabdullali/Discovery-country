package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb",nullable = false)
    Map<LangType, String> name;

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
