package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "homehotels")
public class HomeHotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    String address;
    String contact;
    String mapUrl;
    String type;
    Long viewed;
    Long likeCount;
    Double averageRating = 0.0;
    Integer ratingCount = 0;
    boolean deleted=false;


    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<RoomsEntity> rooms;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;
//
//    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
//    List<RoomReservationEntity> roomReservations;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    RegionEntity region;


}
