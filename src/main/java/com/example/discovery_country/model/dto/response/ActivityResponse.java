package com.example.discovery_country.model.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponse {
    long id;
    @NotBlank(message = "can't be blank")
    String name;
    @NotBlank(message = "can't be blank")
    double price;
    ImageResponseForActivity image;
}



