package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class RegionRequest {

    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    double longitude;

    @Pattern(regexp = "https?://.*", message = "Map URL should be a valid URL")
    String mapUrl;

    @NotNull(message = "Zone ID cannot be null")
    Long zoneId;


}
