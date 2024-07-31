package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    HotelRoomsEntity hotelRoom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    ActivityEntity activity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    ScenicSpotEntity scenicSpot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    RestaurantEntity restaurant;

    Boolean deleted = false;


}
