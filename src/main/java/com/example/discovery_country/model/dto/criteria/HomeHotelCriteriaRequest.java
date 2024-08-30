package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.enums.HomeHotelType;
import com.example.discovery_country.enums.LangType;
import lombok.Data;

@Data
public class HomeHotelCriteriaRequest {
    private String name;
    HomeHotelType type;
    LangType key;
}
