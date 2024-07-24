package com.example.discovery_country.model.dto.response;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {

    String comment;
    
    float rating;
    String fullName;
    String photoUrl;
    ScenicSpotResponse scenicSpot;
    RestaurantResponse restaurant;
    HomeHotelResponse homeHotel;
    UserResponse users;
}