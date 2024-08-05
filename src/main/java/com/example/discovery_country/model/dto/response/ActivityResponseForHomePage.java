package com.example.discovery_country.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponseForHomePage {

    @NotBlank(message = "can't be blank")
    String name;

    @NotBlank(message = "can't be blank")
    double price;

    ImageResponseForActivity image;

}
