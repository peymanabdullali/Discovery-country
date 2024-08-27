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
        boolean deleted;
        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn
        RoomEntity room;

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
