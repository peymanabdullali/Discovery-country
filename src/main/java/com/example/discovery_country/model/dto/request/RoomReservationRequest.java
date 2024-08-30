package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationRequest {

    Map<LangType, String> name;
    LocalDate entryDate;
    LocalDate exitDate;
    byte numberOfGuests;
    Long roomId;
    Long userId;

}
