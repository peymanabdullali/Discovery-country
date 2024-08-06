package com.example.discovery_country.model.dto.response;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ImageResponse {

    long id;
    String name;
    String url;
//    HotelRoomsResponse hotelRoom;
//    HomeHotelResponse homeHotel;
//    ActivityResponse activity;
//    ScenicSpotResponse scenicSpot;
//    RestaurantResponse restaurant;
}
