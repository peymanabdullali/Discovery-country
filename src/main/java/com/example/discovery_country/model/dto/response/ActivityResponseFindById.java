package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponseFindById {

    @NotBlank(message = "can't be blank")
    String name;

    @NotBlank(message = "can't be blank")
    double price;


    ImageResponseForActivity image;

}
