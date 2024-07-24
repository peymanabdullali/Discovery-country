package com.example.discovery_country.model.dto.request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeHotelRequest {

    String name;
    String address;
    String contact;
    String mapUrl;
    double price;
    String type;
    Long viewed;
    List<Long> hotelRoomIds;
    List<Long> imageIds;
    List<Long> reviewIds;
    List<Long> roomReservations;
    Long regionId;

    @Column(columnDefinition = "TEXT")
    String description;
}
