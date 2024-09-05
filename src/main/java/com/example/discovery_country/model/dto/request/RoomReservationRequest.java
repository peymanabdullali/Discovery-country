package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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


    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @NotNull(message = "Entry date cannot be null")
    LocalDate entryDate;

    @NotNull(message = "Exit date cannot be null")
    LocalDate exitDate;

    @Min(value = 1, message = "Number of guests must be at least 1")
    byte numberOfGuests;

    @NotNull(message = "Room ID cannot be null")
    Long roomId;

    @NotNull(message = "User ID cannot be null")
    Long userId;

}
