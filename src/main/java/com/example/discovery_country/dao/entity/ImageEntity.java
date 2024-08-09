package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String url;
    Status imageStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RoomsEntity room;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ActivityEntity activity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ScenicSpotEntity scenicSpot;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RestaurantEntity restaurant;

    Boolean deleted = false;


}
