package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @Min(value =0,message ="Price must be non-negative")
    double price;

    @Min(value = 1, message = "Room number must be at least 1")
    byte roomNumber;

    @Min(value = 1, message = "Room count must be at least 1")
    byte roomCount;

    @NotNull(message = "Room type cannot be null")
    RoomType roomType;

    @NotNull(message = "Home hotel ID cannot be null")
    Long homeHotelId;

    @NotEmpty(message = "Image IDs cannot be empty")

    List<Long> imageIds;


    @NotBlank(message = "Amenities can't be blank")
    String amenities;

}
