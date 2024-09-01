package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRoomsRequest {

    Map<LangType, String> name;
    double price;
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    Long homeHotelId;
    List<Long> imageIds;


    @NotBlank(message = "can't be blank")
    String amenities;//json b

}
