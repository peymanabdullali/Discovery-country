package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "room_reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate entryDate;
    LocalDate exitDate;
    byte totalDay;
    byte numberOfGuests;
    boolean status;
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn
//    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RoomEntity room;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    UserEntity user;


}
