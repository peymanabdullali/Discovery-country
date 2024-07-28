package com.example.discovery_country.model.dto.criteria;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryCriteriaRequest {

    String name;
}
