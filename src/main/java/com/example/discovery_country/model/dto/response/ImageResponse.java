package com.example.discovery_country.model.dto.response;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {


    String name;
    String url;
    HotelRoomsResponse hotelRoom;
    HomeHotelResponse homeHotel;
    ActivityResponse activity;
    ScenicSpotResponse scenicSpot;
    RestaurantResponse restaurant;
}
