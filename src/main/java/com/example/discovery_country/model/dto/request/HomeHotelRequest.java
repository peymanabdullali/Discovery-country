package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.HomeHotelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    HomeHotelType type;
    List<Long> imageIds;
    Long regionId;

    @NotBlank(message = "can't be blank")
    String description;
}
