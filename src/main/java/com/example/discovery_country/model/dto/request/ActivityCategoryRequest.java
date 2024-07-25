package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryRequest {



    @NotBlank(message = "can't be blank")
    String name;

    Long regionId;
    List<Long>  activityIds;

}
