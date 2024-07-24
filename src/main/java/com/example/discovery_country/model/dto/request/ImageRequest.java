package com.example.discovery_country.model.dto.request;

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
public class ImageRequest {


    String name;
    String url;
    Long hotelRoomId;
    Long homeHotelId;
    Long activityId;
    Long scenicSpotId;
    Long restaurantId;
}
