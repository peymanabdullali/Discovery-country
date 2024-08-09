package com.example.discovery_country.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationResponse {

    String name;
    LocalDate entryDate;
    LocalDate exitDate;
    byte totalDay;
    byte numberOfGuests;

}
