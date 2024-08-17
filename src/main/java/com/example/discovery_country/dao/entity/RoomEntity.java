package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    @Column(columnDefinition = "TEXT")
    String amenities;
    byte roomNumber;
    byte roomCount;
    @Enumerated(EnumType.STRING)
    RoomType roomType;
    boolean available;
    boolean deleted;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<RoomReservationEntity> roomReservations;



}
