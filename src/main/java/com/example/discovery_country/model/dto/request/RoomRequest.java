package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequest {


    Map<LangType, String> name;
    double price;
    Map<LangType, String> amenities;
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    Long homeHotelId;
    List<Long> imageIds;
}
