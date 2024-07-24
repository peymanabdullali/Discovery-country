package com.example.discovery_country.model.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeHotelResponse {

    String name;
    String address;
    String contact;
    String mapUrl;
    double price;
    String type;
    Long viewed;
    List<HotelRoomsResponse> hotelRooms;
    List<ImageResponse> images;
    List<ReviewResponse> reviews;
    List<RoomReservationResponse> roomReservations;
    RegionResponse regions;

    String description;
}
