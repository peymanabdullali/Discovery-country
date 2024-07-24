package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "hotel_rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRoomsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    @Column(columnDefinition = "TEXT")
    String amenities;//json b
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    boolean available;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @OneToMany(mappedBy = "hotelRoom", cascade = CascadeType.ALL)
    List<ImageEntity> imageEntities;

    @OneToMany(mappedBy = "hotelRoom", cascade = CascadeType.ALL)
    List<RoomReservationEntity> roomReservations;


}
