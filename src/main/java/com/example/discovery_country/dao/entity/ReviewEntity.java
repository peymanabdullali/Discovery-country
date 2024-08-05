package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    float rating;
    @Column(columnDefinition = "TEXT")
    String comment;
    String fullName;
    String photoUrl;
    boolean status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ScenicSpotEntity scenicSpot;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RestaurantEntity restaurant;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    UserEntity user;


}