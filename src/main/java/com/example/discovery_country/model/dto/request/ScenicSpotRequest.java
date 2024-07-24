package com.example.discovery_country.model.dto.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenicSpotRequest {

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    Long regionId;
    Long imageId;
    Long viewed;

}
