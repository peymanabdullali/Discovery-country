package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantRequestForUpdate {

    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @NotEmpty(message = "Description cannot be empty")
    Map<LangType, String> description;

    @NotEmpty(message = "Address cannot be empty")
    Map<LangType, String> address;

    @NotBlank(message = "can't be blank")
    String contact;

}
