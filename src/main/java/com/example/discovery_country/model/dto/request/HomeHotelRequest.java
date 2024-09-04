package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.HomeHotelType;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotEmpty(message = "Name can't be empty")
    Map<LangType, String> name;

    @NotEmpty(message = "Description can't be empty")
    Map<LangType, String>  description;

    @NotEmpty(message = "Address can't be empty")
    Map<LangType, String> address;

    @Pattern(regexp = "^\\+994[0-9]{9}$", message = "Contact number must be a valid Azerbaijani phone number starting with +994")
    @NotBlank(message = "Contact number can't be blank")
    String contact;


    @Min(value = 0, message = "Price must be positive")
    double price;

    @NotNull(message = "Type can't be null")
    HomeHotelType type;

    @NotEmpty(message = "Image IDs can't be empty")
    List<Long> imageIds;

    @NotNull(message = "Region ID can't be null")
    Long regionId;

    @Pattern(regexp = "https?://.*", message = "Map URL should be a valid URL")
    String mapUrl;
}
