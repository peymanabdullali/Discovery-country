package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.enums.RoomType;
import lombok.Data;

@Data
public class RoomCriteriaRequest {
    private String name;
    private Integer roomCount;
    private RoomType roomType;
    private Double minPrice;
    private Double maxPrice;
}
