package com.example.discovery_country.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequestForHomeHotel {
    @NotBlank(message = "can't be blank")
    String comment;

    float rating;
    String fullName;

    @Pattern(regexp = "https?://.*", message = "Photo  URL should be a valid URL")
    String photoUrl;

    @NotNull(message = "Home-Hotel ID cannot be null")
    Long homeHotelId;

    @NotNull(message = "User ID cannot be null")
    Long userId;
}