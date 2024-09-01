package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.HomeHotelType;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeHotelRequest {

    Map<LangType, String> name;
    @NotBlank(message = "can't be blank")
    Map<LangType, String>  description;
    Map<LangType, String> address;
    String contact;
    String mapUrl;
    double price;
    HomeHotelType type;
    List<Long> imageIds;
    Long regionId;

}
