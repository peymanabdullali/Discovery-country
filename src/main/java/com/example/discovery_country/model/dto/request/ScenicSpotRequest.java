package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ScenicSpotRequest {

    @NotEmpty(message = "Name cannot be empty")
    Map<LangType, String> name;

    @NotEmpty(message = "Description cannot be empty")
    Map<LangType, String> description;

    @NotNull(message = "Region ID cannot be null")
    Long regionId;

    @NotEmpty(message = "Image IDs cannot be empty")
    List<Long> imageIds;

}
