package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.enums.RoomType;
import com.example.discovery_country.model.dto.response.HomeHotelResponseForRoom;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponseFindById {

    Long id;
    String name;
    double price;
    String amenities;
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    boolean available;
    HomeHotelResponseForRoom homeHotel;
    List<ImageResponseForRelations> images;
}
