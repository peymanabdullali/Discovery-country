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
    double price;
    String type;
    Long viewed;
    Long likeCount;
    Double averageRating;


    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<HotelRoomsEntity> hotelRooms;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<ImageEntity> imageEntities;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    List<RoomReservationEntity> roomReservations;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    RegionEntity region;


}
