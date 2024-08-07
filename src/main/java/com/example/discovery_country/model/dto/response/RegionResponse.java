package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionResponse {
    long id;
    String name;
    String mapUrl;

}
