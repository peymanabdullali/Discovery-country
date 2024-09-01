package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.enums.LangType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CriteriaRequestForName {
    String name;
    LangType key;
}
