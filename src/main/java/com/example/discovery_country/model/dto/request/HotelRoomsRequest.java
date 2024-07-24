package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRoomsRequest {

    String name;
    double price;
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    boolean available;
    Long homeHotelId;
    List<Long> imageIds;
    List<Long> roomReservationIds;

    @NotBlank(message = "can't be blank")
    String amenities;//json b

}
