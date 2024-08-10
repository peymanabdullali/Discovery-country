package com.example.discovery_country.model.dto.criteria;

import lombok.Data;

@Data
public class RoomCriteriaRequest {
    private String name;
    private Integer roomCount;
    private String roomType;
    private Double minPrice;
    private Double maxPrice;
}
