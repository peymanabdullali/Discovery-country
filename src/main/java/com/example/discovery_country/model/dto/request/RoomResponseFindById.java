package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.enums.RoomType;
import com.example.discovery_country.model.dto.response.HomeHotelResponseForRoom;
import com.example.discovery_country.model.dto.response.ImageResponseForRoom;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    List<ImageResponseForRoom> images;
}
