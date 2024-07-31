package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HotelRoomsEntity hotelRoom;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    UserEntity user;


}
