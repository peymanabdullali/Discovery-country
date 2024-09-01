package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.enums.LangType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.codec.language.bm.Lang;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantCriteriaRequest {
    String name;
    double rating;
    LangType key;
}
