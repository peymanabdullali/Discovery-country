package com.example.discovery_country.model.dto.request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationRequest {

    String name;
    LocalDate entryDate;
    LocalDate exitDate;
    byte numberOfGuests;
    Long roomId;
    Long userId;

}
