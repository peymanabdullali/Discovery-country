package com.example.discovery_country.model.dto.criteria;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantCriteriaRequest {
    String name;
    double rating;
}
