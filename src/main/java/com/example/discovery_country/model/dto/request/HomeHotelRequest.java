package com.example.discovery_country.model.dto.request;

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
    String type;
    List<Long> imageIds;
    Long regionId;

    @NotBlank(message = "can't be blank")
    String description;
}
