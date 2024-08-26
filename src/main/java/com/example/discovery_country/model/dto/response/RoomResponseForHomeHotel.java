package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponseForHomeHotel {

    Long id;
    String name;
    double price;
    byte roomCount;
    RoomType roomType;


}
