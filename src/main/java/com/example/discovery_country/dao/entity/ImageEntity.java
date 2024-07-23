package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HotelRoomsEntity hotelRoom;

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
}
