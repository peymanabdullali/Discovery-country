package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "can't be blank")
    Map<LangType, String> name;
    double latitude;
    double longitude;
    String mapUrl;

    Long zoneId;



}
