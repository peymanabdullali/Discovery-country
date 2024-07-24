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

    @Column(columnDefinition = "TEXT")
    String comment;
    
    float rating;
    String fullName;
    String photoUrl;
    Long scenicSpotId;
    Long restaurantId;
    Long homeHotelId;
    Long userId;
}