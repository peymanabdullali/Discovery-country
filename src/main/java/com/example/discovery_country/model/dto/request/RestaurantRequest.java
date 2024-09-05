package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantRequest {

    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @NotEmpty(message = "Description cannot be empty")
    Map<LangType, String> description;

    @NotEmpty(message = "Address cannot be empty")
    Map<LangType, String> address;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Contact must be a valid phone number")
    @NotBlank(message = "Contact cannot be blank")
    String contact;

    @Pattern(regexp = "https?://.*", message = "Map URL should be a valid URL")
    String mapUrl;

    @Pattern(regexp = "https?://.*", message = "Menu URL should be a valid URL")
    String menuUrl;

    @NotNull(message = "Region ID cannot be null")
    Long regionId;

    @NotEmpty(message = "Image IDs cannot be empty")
    List<Long> imageIds;
}
