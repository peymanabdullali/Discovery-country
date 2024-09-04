package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryRequest {



    @NotEmpty(message = "Name can't be empty")
    Map<LangType,String>name;

    @NotEmpty(message = "Name can't be empty")
    List<Long> regionIds;

}
