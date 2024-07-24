package com.example.discovery_country.model.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {

    @NotBlank(message = "can't be blank")
    String comment;

    float rating;
    String fullName;
    String photoUrl;
    Long scenicSpotId;
    Long restaurantId;
    Long homeHotelId;
    Long userId;
}